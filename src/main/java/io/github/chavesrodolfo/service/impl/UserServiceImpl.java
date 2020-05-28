package io.github.chavesrodolfo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import io.github.chavesrodolfo.exceptions.UserNotFoundException;
import io.github.chavesrodolfo.model.User;
import io.github.chavesrodolfo.model.dto.UserDTO;
import io.github.chavesrodolfo.repository.UserRepository;
import io.github.chavesrodolfo.service.UserService;
import lombok.extern.slf4j.Slf4j;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import java.lang.reflect.Type;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Page<UserDTO> listUsers(final Integer page, final Integer size) {
        log.info("listUsers page {} size {}", page, size);
        
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.DESC, "username");

        final Page<User> users = userRepository.findActiveUsers(pageRequest);

        final ModelMapper modelMapper = new ModelMapper();
        Type listType = new TypeToken<Page<UserDTO>>(){}.getType();
        Page<UserDTO> userDtoPage = modelMapper.map(users,listType);

        return userDtoPage;
    }

    @Override
    public UserDTO listUsersByUuid(final String uuid) {
        log.info("listUsersByUuid uuid {}", uuid);

        final User user = userRepository.findByUuid(uuid).orElseThrow(() -> new UserNotFoundException(uuid));

        final ModelMapper modelMapper = new ModelMapper();
        final UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        return userDTO;
    }
    
}