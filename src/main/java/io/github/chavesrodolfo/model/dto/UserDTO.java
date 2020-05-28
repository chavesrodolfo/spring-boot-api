package io.github.chavesrodolfo.model.dto;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDTO extends RepresentationModel<UserDTO> {

    private long id;
    private String uuid;
    private String username;
    private boolean active;
    private String roles;
    
}