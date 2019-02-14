package hu.eronix.bookstore.auth.model;

public interface AuthUser<TRole extends AuthRole> {

    String getUsername();

    String getPassword();

    TRole getRole();
}
