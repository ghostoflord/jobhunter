package vn.ghost.jobhunter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.ghost.jobhunter.domain.User;
import vn.ghost.jobhunter.service.UserService;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/create")
    public String createUser() {
        User user = new User();
        user.setEmail("ghost@gmail.com");
        user.setName("ghost");
        user.setPassword("555");
        this.userService.SaveUserHandle(user);
        return "from create user";
    }
}
