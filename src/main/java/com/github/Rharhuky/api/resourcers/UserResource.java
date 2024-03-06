package com.github.Rharhuky.api.resourcers;

import com.github.Rharhuky.api.domain.dto.UserResponse;
import com.github.Rharhuky.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/users")
public class UserResource {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(modelMapper.map(userService.findById(id), UserResponse.class));
    }
    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll(){
        return ResponseEntity.ok( userService.findAll().stream().map(user -> modelMapper.map(user, UserResponse.class)).toList());
    }
}
