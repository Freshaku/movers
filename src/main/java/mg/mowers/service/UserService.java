package mg.mowers.service;

import mg.mowers.entity.User;
import mg.mowers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void save(User user) {
        System.out.println("Saving user: " + user.getUsername());
        userRepository.save(user);
    }
}
