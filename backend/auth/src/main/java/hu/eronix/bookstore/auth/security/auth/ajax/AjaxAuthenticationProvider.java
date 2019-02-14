package hu.eronix.bookstore.auth.security.auth.ajax;

import hu.eronix.bookstore.auth.model.AuthPermission;
import hu.eronix.bookstore.auth.model.AuthRole;
import hu.eronix.bookstore.auth.model.AuthUser;
import hu.eronix.bookstore.auth.model.UserContext;
import hu.eronix.bookstore.auth.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author vladimir.stankovic
 * <p>
 * Aug 3, 2016
 */
@Component
public class AjaxAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private AuthUserService userService;

    @Override
    @Transactional
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.notNull(authentication, "No authentication data provided");

        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        Optional<AuthUser> userOptional = userService.findByUsername(username);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        AuthUser user = userOptional.get();

        /*if (!encoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Authentication Failed. Username or Password not valid.");
        }*/

        if (user.getRole().getPermissions() == null)
            throw new InsufficientAuthenticationException("User has no permissions assigned");

        AuthRole<AuthPermission> role = user.getRole();
        List<GrantedAuthority> authorities = role.getPermissions().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getValue()))
                .collect(Collectors.toList());

        UserContext userContext = UserContext.create(user.getUsername(), authorities);

        return new UsernamePasswordAuthenticationToken(userContext, null, userContext.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
