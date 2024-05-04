package crudjava.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import crudjava.models.User;
import crudjava.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User create(User user) {
        String passwordHashed = this.hashPass(user.getPassword());
        user.setPassword(passwordHashed);
        return userRepository.save(user);
    }

    public Map<String, Object> getAll() {
        List<User> users = userRepository.findAll();
        Map<String, Object> response = new HashMap<>();
        response.put("users", users);
        return response;
    }

    public User findById(Long id) {
        java.util.Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public boolean deleteById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public String hashPass(String pass) {
        return passwordEncoder.encode(pass);
    }
}