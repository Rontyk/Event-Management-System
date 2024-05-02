package com.example.concert.repositories;

import com.example.concert.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByUserId(Long id);
    Optional<User> findUserByUsername(String name);
}
