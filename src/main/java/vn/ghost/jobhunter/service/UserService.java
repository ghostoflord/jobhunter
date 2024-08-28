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

    public void SaveUserHandle(User user) {
        this.userRepository.save(user);
    }
}
