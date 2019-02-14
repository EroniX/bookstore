package hu.eronix.bookstore.auth.security.endpoint;

import hu.eronix.bookstore.auth.model.AuthPermission;
import hu.eronix.bookstore.auth.model.AuthRole;
import hu.eronix.bookstore.auth.model.AuthUser;
import hu.eronix.bookstore.auth.model.UserContext;
import hu.eronix.bookstore.auth.security.auth.jwt.extractor.TokenExtractor;
import hu.eronix.bookstore.auth.security.auth.jwt.verifier.TokenVerifier;
import hu.eronix.bookstore.auth.security.config.JwtSettings;
import hu.eronix.bookstore.auth.security.config.WebSecurityConfig;
import hu.eronix.bookstore.auth.security.exceptions.InvalidJwtToken;
import hu.eronix.bookstore.auth.security.model.token.JwtToken;
import hu.eronix.bookstore.auth.security.model.token.JwtTokenFactory;
import hu.eronix.bookstore.auth.security.model.token.RawAccessJwtToken;
import hu.eronix.bookstore.auth.security.model.token.RefreshToken;
import hu.eronix.bookstore.auth.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * RefreshTokenEndpoint
 *
 * @author vladimir.stankovic
 * <p>
 * Aug 17, 2016
 */
@RestController
public class RefreshTokenEndpoint {
    @Autowired
    private JwtTokenFactory tokenFactory;
    @Autowired
    private JwtSettings jwtSettings;
    @Autowired
    private AuthUserService userService;
    @Autowired
    private TokenVerifier tokenVerifier;
    @Autowired
    @Qualifier("jwtHeaderTokenExtractor")
    private TokenExtractor tokenExtractor;

    @RequestMapping(value = "/api/auth/token", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    JwtToken refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String tokenPayload = tokenExtractor.extract(request.getHeader(WebSecurityConfig.AUTHENTICATION_HEADER_NAME));

        RawAccessJwtToken rawToken = new RawAccessJwtToken(tokenPayload);
        RefreshToken refreshToken = RefreshToken.create(rawToken, jwtSettings.getTokenSigningKey()).orElseThrow(() -> new InvalidJwtToken());

        String jti = refreshToken.getJti();
        if (!tokenVerifier.verify(jti)) {
            throw new InvalidJwtToken();
        }

        String subject = refreshToken.getSubject();
        Optional<AuthUser> userOptional = userService.findByUsername(subject);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException("Student not found: " + subject);
        }

        AuthUser user = userOptional.get();

        if (user.getRole().getPermissions() == null)
            throw new InsufficientAuthenticationException("Student has no permissions assigned");

        AuthRole<AuthPermission> role = user.getRole();
        List<GrantedAuthority> authorities = role.getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getValue()))
                .collect(Collectors.toList());

        UserContext userContext = UserContext.create(user.getUsername(), authorities);

        return tokenFactory.createAccessJwtToken(userContext);
    }
}
