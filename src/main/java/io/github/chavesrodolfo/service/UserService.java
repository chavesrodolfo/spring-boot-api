package io.github.chavesrodolfo.service;

import org.springframework.data.domain.Page;

import io.github.chavesrodolfo.model.dto.UserDTO;

public interface UserService {

	Page<UserDTO> listUsers(Integer page, Integer size);

	UserDTO listUsersByUuid(String uuid);

}
