package hu.eronix.bookstore.auth.service;

import hu.eronix.bookstore.auth.model.AuthUser;

import java.util.Optional;

public interface AuthUserService<TUser extends AuthUser> {
    Optional<TUser> findByUsername(String username);
}
