package hu.eronix.bookstore.auth.security.model.token;

import hu.eronix.bookstore.auth.security.exceptions.JwtExpiredTokenException;
import hu.eronix.bookstore.auth.security.model.Permissions;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.security.authentication.BadCredentialsException;

import java.util.List;
import java.util.Optional;

/**
 * RefreshToken
 *
 * @author vladimir.stankovic
 * <p>
 * Aug 19, 2016
 */
@SuppressWarnings("unchecked")
public class RefreshToken implements JwtToken {
    private Jws<Claims> claims;

    private RefreshToken(Jws<Claims> claims) {
        this.claims = claims;
    }

    /**
     * Creates and validates Refresh token
     *
     * @param token
     * @param signingKey
     * @return
     * @throws BadCredentialsException
     * @throws JwtExpiredTokenException
     */
    public static Optional<RefreshToken> create(RawAccessJwtToken token, String signingKey) {
        Jws<Claims> claims = token.parseClaims(signingKey);

        List<String> permissions = claims.getBody().get("permissions", List.class);
        if (permissions == null || permissions.isEmpty()
                || !permissions.stream().filter(scope -> Permissions.REFRESH_TOKEN.authority().equals(scope)).findFirst().isPresent()) {
            return Optional.empty();
        }

        return Optional.of(new RefreshToken(claims));
    }

    @Override
    public String getToken() {
        return null;
    }

    public Jws<Claims> getClaims() {
        return claims;
    }

    public String getJti() {
        return claims.getBody().getId();
    }

    public String getSubject() {
        return claims.getBody().getSubject();
    }
}
