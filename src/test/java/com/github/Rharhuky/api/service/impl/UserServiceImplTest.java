package com.github.Rharhuky.api.service.impl;

import com.github.Rharhuky.api.domain.User;
import com.github.Rharhuky.api.domain.dto.UserDTO;
import com.github.Rharhuky.api.repositories.UserRepository;
import com.github.Rharhuky.api.service.exceptions.InfoNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    public static final Long ID = 1L;
    public static final String NAME = "Rharhuky";
    public static final String EMAIL = "rharhuky@gmail.com";
    public static final String PASSWORD = "3333";
    public static final String INFO_NOT_FOUND = "Info not found";
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    private User user;
    private UserDTO userDTO;

    private Optional<User> optionalUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    @DisplayName(value = "Return user Instance")
    void whenFindByIdReturnUserInstance() {
        when(userRepository.findById(anyLong())).thenReturn(optionalUser);
        User theUser = userService.findById(ID);
        assertNotNull(theUser, "Should be not Null");
        assertEquals(User.class, theUser.getClass());
        assertEquals(ID, theUser.getId());

    }

    @Test
    @DisplayName(value = "Throw Info Not Found Exception")
    void whenFindByIdReturnsObjectNotFoundException(){
        when(userRepository.findById(anyLong())).thenThrow(new InfoNotFoundException(INFO_NOT_FOUND));
        try{
            userService.findById(ID);
        }
        catch (Exception exception){
            assertEquals(InfoNotFoundException.class, exception.getClass());
            assertEquals(INFO_NOT_FOUND, exception.getMessage());
        }
    }



    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUser(){
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }
}