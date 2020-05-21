package io.github.chavesrodolfo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

        user.orElseThrow(() -> new UsernameNotFoundException(String.format("Username [%s] not found.", username)));

        log.debug(String.format("Username [%s] located.", username));

        return user.map(CustomUserDetails::new).get();
    }

}