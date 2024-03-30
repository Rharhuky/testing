package com.github.Rharhuky.api.service.impl;

import com.github.Rharhuky.api.domain.User;
import com.github.Rharhuky.api.domain.dto.UserDTO;
import com.github.Rharhuky.api.repositories.UserRepository;
import com.github.Rharhuky.api.service.exceptions.DataIntegrityViolationException;
import com.github.Rharhuky.api.service.exceptions.InfoNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplTest {

    public static final Long ID = 1L;
    public static final String NAME = "Rharhuky";
    public static final String EMAIL = "rharhuky@gmail.com";
    public static final String PASSWORD = "3333";
    public static final String INFO_NOT_FOUND = "Info not found";
    public static final String EMAIL_JA_CADASTRADO = "Email já cadastrado :/";
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
    @Tag(value = "Erros")
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
    @DisplayName(value = "Return An List of Users")
    void findAll() {
        when(userService.findAll()).thenReturn(List.of(user));

        var theUsers = userService.findAll();

        assertNotNull(theUsers, "It should not be Null bro :/");

        var theUser = theUsers.get(0);

        assertEquals(ID, theUser.getId());
        assertEquals(NAME, theUser.getName());
        assertEquals(EMAIL, theUser.getEmail());
        assertEquals(PASSWORD, theUser.getPassword());

    }

    @Test
    @DisplayName(value = "Sucess on Create an User")
    void create() {
        when(userRepository.save(any())).thenReturn(user);

        var anUser = userService.create(userDTO);

        assertNotNull(anUser, "Should Be not null !!!");
        assertEquals(User.class, anUser.getClass());
        assertEquals(ID, anUser.getId());
    }

    @Test
    @DisplayName(value = "Throw DataIntegrityViolationException on Create User")
    @Tag(value = "Erros")
    void createWithSameEmail(){
        when(userRepository.findByEmail(anyString())).thenReturn(optionalUser);

        try{
            var anUser = userService.create(userDTO);

        }
        catch (Exception exception){
            assertEquals(DataIntegrityViolationException.class, exception.getClass());
            assertEquals(EMAIL_JA_CADASTRADO, exception.getMessage());
        }
    }

    @Test
    @DisplayName(value = "UpdateWithSucess")
    void updateWithSucess() {
        when(userRepository.save(any())).thenReturn(user);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        var anUser = userService.update(user.getId(), userDTO);

        assertNotNull(anUser, "Should Be not null !!!");
        assertEquals(User.class, anUser.getClass());
        assertEquals(ID, anUser.getId());
    }
    @Test
    @DisplayName(value = "UpdateWithNoSucess")
    void updateWithoutSucess() {
        when(userRepository.findByEmail(anyString())).thenReturn(optionalUser);

        try{
            var anUser = userService.create(userDTO);

        }
        catch (Exception exception){
            assertEquals(DataIntegrityViolationException.class, exception.getClass());
            assertEquals(EMAIL_JA_CADASTRADO, exception.getMessage());
        }
    }

    @Test
    void deleteWithSucess() {
        when(userRepository.findById(anyLong())).thenReturn(optionalUser);
        doNothing().when(userRepository).delete(any());
        userService.delete(userDTO.getId());
        verify(userRepository, times(1)).deleteById(any());

    }
    @Test
    void deleteWithoutSucess() {
        when(userRepository.findById(anyLong())).thenThrow(new InfoNotFoundException(INFO_NOT_FOUND));
        try{
            userService.delete(ID);
        } catch (Exception e) {
            assertEquals(INFO_NOT_FOUND, e.getMessage());
        }

    }

    private void startUser(){
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }
}