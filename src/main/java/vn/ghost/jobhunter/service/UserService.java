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

    public List<User> fetchAllUser() {
        return this.userRepository.findAll();
    }

    public User fetchUserById(long id) {
        Optional<User> userOptional = this.userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        return null;
    }

    public User handleUpdateUser(User reqUser) {
        User currentUser = this.fetchUserById(reqUser.getId());
        if (currentUser != null) {
            currentUser.setEmail(reqUser.getEmail());
            currentUser.setName(reqUser.getName());
            currentUser.setPassword(reqUser.getPassword());
            // save update
            currentUser = this.userRepository.save(currentUser);
        }
        return currentUser;
    }

    public User SaveUserHandle(User user) {
        return this.userRepository.save(user);
    }

    public void DeleteUser(long id) {
        this.userRepository.deleteById(id);
    }
}
