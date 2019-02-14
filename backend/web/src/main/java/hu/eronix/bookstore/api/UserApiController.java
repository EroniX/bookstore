package hu.eronix.bookstore.api;

import hu.eronix.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserApiController {

    private final UserService userService;

    @Autowired
    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/isUsernameExists/{username}/")
    public ResponseEntity<Boolean> isUsernameExist(@PathVariable String username) {
        return ResponseEntity.ok(userService.isUsernameExist(username));
    }

    @GetMapping("/isEmailExists/{email}/")
    public ResponseEntity<Boolean> isEmailExist(@PathVariable String email) {
        return ResponseEntity.ok(userService.isEmailExist(email));
    }
}
