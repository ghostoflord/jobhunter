package vn.ghost.jobhunter.controller;

import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping("/user/create")
    public User createUser(@RequestBody User postManUser) {
        User ghostUser = this.userService.SaveUserHandle(postManUser);
        return ghostUser;
    }
}
