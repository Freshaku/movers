package mg.mowers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mg.mowers.entity.User;
import mg.mowers.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void save(User user) {
        System.out.println("Saving user: " + user.getName());
        userRepository.save(user);
    }
}
