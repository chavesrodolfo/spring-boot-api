package io.github.chavesrodolfo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import io.github.chavesrodolfo.repository.UserRepository;
import io.github.chavesrodolfo.model.CustomUserDetails;
import io.github.chavesrodolfo.model.User;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException(String.format("Username not found. [%s]", username)));
        log.debug(String.format("Username located. [%s]", username));
        return user.map(CustomUserDetails::new).get();
    }

	public void changePassword(String username, String oldPassword, String newPassword) throws UsernameNotFoundException, BadCredentialsException {
        Optional<User> user = userRepository.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException(String.format("Username not found. [%s]", username)));
        log.debug(String.format("Username located. [%s]", username));

        verifyPassword(username, oldPassword, user);

        user.get().setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt(4)));
        userRepository.save(user.get());
        log.info(String.format("Password updated. [%s]", username));
	}

    private void verifyPassword(String username, String oldPassword, Optional<User> user) {
        if (BCrypt.checkpw(oldPassword, user.get().getPassword())) {
            log.debug(String.format("Password match. [%s]",username));
        }
        else {
            String errorMessage = String.format("Password does not match. [%s]", username);
            log.debug(errorMessage);
            throw new BadCredentialsException(errorMessage);
        }
    }

}