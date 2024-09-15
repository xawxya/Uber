package uber.datastructure.user;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/uber/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody UserDTO userdto) {
        User user1 = new User(
                userdto.getPhoneNumber(),
                userdto.getStatus(),
                userdto.getPassword(),
                userdto.getUsername()
        );

        User savedUser = userService.register(user1);
        return ResponseEntity.ok(savedUser);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        this.userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
