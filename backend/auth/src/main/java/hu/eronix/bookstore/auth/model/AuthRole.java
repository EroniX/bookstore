package hu.eronix.bookstore.auth.model;

import java.util.List;

public interface AuthRole<TPermission extends AuthPermission> {
    List<TPermission> getPermissions();
}
