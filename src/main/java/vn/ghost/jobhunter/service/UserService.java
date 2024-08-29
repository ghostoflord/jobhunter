package vn.ghost.jobhunter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import vn.ghost.jobhunter.domain.User;
import vn.ghost.jobhunter.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> GetUserHandle() {
        return this.userRepository.findAll();
    }

    public Optional<User> GetUserById(long id) {
        return this.userRepository.findById(id);
    }

    public User SaveUserHandle(User user) {
        return this.userRepository.save(user);
    }

    public void DeleteUser(long id) {
        this.userRepository.deleteById(id);
    }
}
