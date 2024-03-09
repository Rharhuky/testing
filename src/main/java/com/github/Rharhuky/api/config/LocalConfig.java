package com.github.Rharhuky.api.config;

import com.github.Rharhuky.api.domain.User;
import com.github.Rharhuky.api.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@AllArgsConstructor
@Configuration
@Profile("local")
public class LocalConfig {

    private final UserRepository userRepository;

    @PostConstruct
    public void startDB(){
        User userA = new User(null, "Rharhuky", "rharhuky@gmail.com", "3333");
        User userB = new User(null, "Allex", "alllex@gmail.com", "3333");
        userRepository.saveAll(List.of(userB, userA));
    }

}
