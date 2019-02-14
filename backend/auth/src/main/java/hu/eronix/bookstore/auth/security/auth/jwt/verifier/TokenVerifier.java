package hu.eronix.bookstore.auth.security.auth.jwt.verifier;

/**
 * @author vladimir.stankovic
 * <p>
 * Aug 17, 2016
 */
public interface TokenVerifier {
    boolean verify(String jti);
}
