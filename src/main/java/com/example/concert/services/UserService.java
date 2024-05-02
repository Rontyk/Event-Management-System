package com.example.concert.services;

import com.example.concert.dto.request.UserRequestDTO;
import com.example.concert.entities.User;
import com.example.concert.repositories.UserRepository;
import com.example.concert.security.UserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean checkToAuth(Authentication authentication){
        return authentication == null;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void saveUser(UserRequestDTO userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPasswordHash(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User updateUser(Long id, UserRequestDTO userUpdates) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(userUpdates.getUsername());
                    user.setPasswordHash(userUpdates.getPassword());
                    user.setEmail(userUpdates.getEmail());
                    return userRepository.save(user);
                }).orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

}

