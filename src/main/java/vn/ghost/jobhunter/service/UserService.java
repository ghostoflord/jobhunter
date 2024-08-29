package vn.ghost.jobhunter.service;

import org.springframework.stereotype.Service;

import vn.ghost.jobhunter.domain.User;
import vn.ghost.jobhunter.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User SaveUserHandle(User user) {
       return this.userRepository.save(user);
    }
}
