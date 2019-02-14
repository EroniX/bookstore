package hu.eronix.bookstore.service;

import hu.eronix.bookstore.auth.model.UserContext;
import hu.eronix.bookstore.exceptions.NotAuthenticatedException;
import hu.eronix.bookstore.model.entity.User;
import hu.eronix.bookstore.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class SecurityServiceImpl implements SecurityService {

    private static Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

    private final UserRepository userRepository;

    @Autowired
    public SecurityServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User getUser() throws NotAuthenticatedException {
        String username = getUsername();
        if (username != null) {
            Optional<User> userOptional = userRepository.findByUsername(username);
            if (userOptional.isPresent()) {
                return userOptional.get();
            }
        }
        logger.warn("User is not authenticated");
        throw new NotAuthenticatedException();
    }

    @Override
    public Boolean isAuthenticated() {
        return getUsername() != null;
    }

    @Override
    public String getUsername() {
        try {
            Authentication authentication = SecurityContextHolder
                    .getContext()
                    .getAuthentication();

            Object userContext = authentication.getPrincipal();
            if (userContext instanceof UserContext) {
                return ((UserContext) userContext).getUsername();
            }
        } catch (Exception e) {
            logger.error("Exception happened during getting the username", e);
        }
        return null;
    }

    @Override
    @Transactional
    public Boolean hasPermission(String permission) throws NotAuthenticatedException {
        return getUser().getRole()
                .getPermissions()
                .stream()
                .anyMatch(n -> n.getValue().equals(permission));
    }
}