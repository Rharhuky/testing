package com.github.Rharhuky.api.service;

import com.github.Rharhuky.api.domain.User;
import com.github.Rharhuky.api.domain.dto.UserDTO;

import java.util.List;

public interface UserService {

    User findById(Long id);

    List<User> findAll();

    User create(UserDTO userDTO);

    User update(Long id, UserDTO userDTO);

    void delete(Long id);
}
