package com.github.Rharhuky.api.resourcers;

import com.github.Rharhuky.api.domain.User;
import com.github.Rharhuky.api.domain.dto.UserDTO;
import com.github.Rharhuky.api.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserResourceTest {

    public static final Long ID = 1L;
    public static final String NAME = "Rharhuky";
    public static final String EMAIL = "rharhuky@gmail.com";
    public static final String PASSWORD = "202020";
    public static final int DEFAULT_INDEX = 0;

    private User user;

    private UserDTO userDTO;


    @InjectMocks
    private UserResource userResource;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    @DisplayName(value = "Return Success when FindById")
    void findById() {
        when(userService.findById(anyLong())).thenReturn(user);
        when(modelMapper.map(any(), any())).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = userResource.findById(ID);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UserDTO.class, response.getBody().getClass());

        var theUser = response.getBody();

        assertEquals(ID, theUser.getId());
        assertEquals(NAME, theUser.getName());
        assertEquals(EMAIL, theUser.getEmail());
        assertEquals(PASSWORD, theUser.getPassword());

    }

    @Test
    @DisplayName(value = "Return List of UserDTO with successfully")
    void findAll() {
        when(userService.findAll()).thenReturn(List.of(user));
        when(modelMapper.map(any(), any())).thenReturn(userDTO);

        ResponseEntity<List<UserDTO>> usersDTO = userResource.findAll();

        assertNotNull(usersDTO);
        assertNotNull(usersDTO.getBody());
        assertEquals(HttpStatus.OK, usersDTO.getStatusCode());
        assertEquals(ResponseEntity.class, usersDTO.getClass());
        assertEquals(ArrayList.class, usersDTO.getBody().getClass());
        assertNotNull(usersDTO.getBody().get(DEFAULT_INDEX));
        assertEquals(UserDTO.class, usersDTO.getBody().get(DEFAULT_INDEX).getClass());
        assertEquals(ID, usersDTO.getBody().get(DEFAULT_INDEX).getId());
        assertEquals(EMAIL, usersDTO.getBody().get(DEFAULT_INDEX).getEmail());
        assertEquals(PASSWORD, usersDTO.getBody().get(DEFAULT_INDEX).getPassword());
    }

    @Test
    @DisplayName(value = "Create user with successfully")
    void create() {
        when(userService.create(any(UserDTO.class))).thenReturn(user);

        ResponseEntity<UserDTO> userDTOResponseEntity = userResource.create(userDTO);

        assertNotNull(userDTOResponseEntity);
        assertEquals(HttpStatus.CREATED, userDTOResponseEntity.getStatusCode());
        assertEquals(ResponseEntity.class, userDTOResponseEntity.getClass());
        assertNull(userDTOResponseEntity.getBody());
        assertNotNull(userDTOResponseEntity.getHeaders().get("Location"));
    }

    @Test
    @DisplayName(value = "UPDATE user with Successfully")
    void update() {
        when(modelMapper.map(any(), any())).thenReturn(userDTO);
        when(userService.update(ID, userDTO)).thenReturn(user);

        ResponseEntity<UserDTO> userDTOResponseEntity = userResource.update(ID, userDTO);

        assertNotNull(userDTOResponseEntity);
        assertEquals(ResponseEntity.class, userDTOResponseEntity.getClass());
        assertEquals(HttpStatus.OK, userDTOResponseEntity.getStatusCode());
        assertNotNull(userDTOResponseEntity.getBody());
        assertEquals(EMAIL, userDTOResponseEntity.getBody().getEmail());
    }

    @Test
    @DisplayName(value = "Delete with Successfully")
    void delete() {
        doNothing().when(userService).delete(anyLong());
        ResponseEntity<UserDTO> userDTOResponseEntity = userResource.delete(ID);
        assertNotNull(userDTOResponseEntity);
        assertEquals(HttpStatus.NO_CONTENT, userDTOResponseEntity.getStatusCode());
        verify(userService, times(1)).delete(anyLong());
    }

    private void startUser(){
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
    }

}