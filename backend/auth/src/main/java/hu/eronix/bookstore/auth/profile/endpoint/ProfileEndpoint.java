package hu.eronix.bookstore.auth.profile.endpoint;

import hu.eronix.bookstore.auth.model.UserContext;
import hu.eronix.bookstore.auth.security.auth.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * End-point for retrieving logged-in user details.
 *
 * @author vladimir.stankovic
 * <p>
 * Aug 4, 2016
 */
@RestController
public class ProfileEndpoint {
    @RequestMapping(value = "/api/me", method = RequestMethod.GET)
    public @ResponseBody
    UserContext get(JwtAuthenticationToken token) {
        return (UserContext) token.getPrincipal();
    }
}
