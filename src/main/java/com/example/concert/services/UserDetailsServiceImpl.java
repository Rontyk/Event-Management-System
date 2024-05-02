package com.example.concert.services;

import com.example.concert.entities.User;
import com.example.concert.repositories.UserRepository;
import com.example.concert.security.UserDetails;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByUsername(username);
        return user.map(UserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
