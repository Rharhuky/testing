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
}
