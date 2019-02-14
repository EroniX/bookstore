package hu.eronix.bookstore.service;

import hu.eronix.bookstore.auth.service.AuthUserService;
import hu.eronix.bookstore.model.entity.User;
import hu.eronix.bookstore.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, AuthUserService<User> {

    private static Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Boolean isUsernameExist(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    @Transactional
    public Boolean isEmailExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    @Transactional
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
