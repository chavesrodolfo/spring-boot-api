package io.github.chavesrodolfo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.chavesrodolfo.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    
    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u where u.username = :username and u.password = :password") 
    Optional<User> findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
    
}