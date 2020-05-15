package io.github.chavesrodolfo.config.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.chavesrodolfo.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    
    Optional<User> findByUsername(String username);
    
}