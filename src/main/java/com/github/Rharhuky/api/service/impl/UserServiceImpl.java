package com.github.Rharhuky.api.service.impl;


import com.github.Rharhuky.api.domain.User;
import com.github.Rharhuky.api.domain.dto.UserDTO;
import com.github.Rharhuky.api.repositories.UserRepository;
import com.github.Rharhuky.api.service.UserService;
import com.github.Rharhuky.api.service.exceptions.DataIntegratyViolationException;
import com.github.Rharhuky.api.service.exceptions.InfoNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new InfoNotFoundException(("Info nto found")));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(UserDTO userDTO) {
        findByEmail(userDTO);
        return userRepository.save(modelMapper.map(userDTO, User.class));
    }

    private void findByEmail(UserDTO userDTO){
        userRepository.findByEmail(userDTO.getEmail()).ifPresent(user -> {
            throw new DataIntegratyViolationException("Email já cadastrado :/");
        });
    }
}
