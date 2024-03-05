package com.github.Rharhuky.api.service.impl;


import com.github.Rharhuky.api.domain.User;
import com.github.Rharhuky.api.repositories.UserRepository;
import com.github.Rharhuky.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(new User());
    }
}
