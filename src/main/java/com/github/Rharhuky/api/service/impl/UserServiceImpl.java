package com.github.Rharhuky.api.service.impl;


import com.github.Rharhuky.api.domain.User;
import com.github.Rharhuky.api.repositories.UserRepository;
import com.github.Rharhuky.api.service.UserService;
import com.github.Rharhuky.api.service.exceptions.InfoNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new InfoNotFoundException(("Info nto found")));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
