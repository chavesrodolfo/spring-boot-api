package io.github.chavesrodolfo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor @NoArgsConstructor
public class AuthenticationRequest {
    
    private String username;
    private String password;
}