package hu.eronix.bookstore.service;

import hu.eronix.bookstore.exceptions.NotAuthenticatedException;
import hu.eronix.bookstore.model.entity.User;

public interface SecurityService {

    User getUser() throws NotAuthenticatedException;

    Boolean isAuthenticated();

    String getUsername();

    Boolean hasPermission(String permission) throws NotAuthenticatedException;
}
