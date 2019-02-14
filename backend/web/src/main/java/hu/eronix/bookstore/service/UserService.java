package hu.eronix.bookstore.service;

public interface UserService {

    Boolean isUsernameExist(String username);

    Boolean isEmailExist(String email);
}
