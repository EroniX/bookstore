package hu.eronix.bookstore.auth.security.model;

/**
 * Permissions
 *
 * @author vladimir.stankovic
 * <p>
 * Aug 18, 2016
 */
public enum Permissions {
    REFRESH_TOKEN;

    public String authority() {
        return "ROLE_" + this.name();
    }
}
