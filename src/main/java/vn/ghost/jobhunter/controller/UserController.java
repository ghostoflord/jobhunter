package vn.ghost.jobhunter.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import vn.ghost.jobhunter.domain.User;
import vn.ghost.jobhunter.service.UserService;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public List<User> getUser() {
        List<User> hadesUser = this.userService.GetUserHandle();
        return hadesUser;
    }

    @GetMapping("/user/{id}")
    public Optional<User> getUserById(@PathVariable("id") long id) {
        Optional<User> getUserId = this.userService.GetUserById(id);
        return getUserId;
    }

    @PostMapping("/user")
    public User createUser(@RequestBody User postManUser) {
        User ghostUser = this.userService.SaveUserHandle(postManUser);
        return ghostUser;
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        this.userService.DeleteUser(id);
        return "succcess";
    }
}
