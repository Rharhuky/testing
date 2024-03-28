package com.github.Rharhuky.api.service.impl;


import com.github.Rharhuky.api.domain.User;
import com.github.Rharhuky.api.domain.dto.UserDTO;
import com.github.Rharhuky.api.repositories.UserRepository;
import com.github.Rharhuky.api.service.UserService;
import com.github.Rharhuky.api.service.exceptions.DataIntegratyViolationException;
import com.github.Rharhuky.api.service.exceptions.InfoNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private  ModelMapper modelMapper;

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new InfoNotFoundException(("Info not found")));
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

    @Override
    public User update(Long id, UserDTO userDTO) {
        var user = userRepository.findById(id).orElseThrow(() -> new InfoNotFoundException("Info not found"));
        userDTO.setId(id);
        modelMapper.map(userDTO, user);
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        findById(id);
        userRepository.deleteById(id);
    }
}
