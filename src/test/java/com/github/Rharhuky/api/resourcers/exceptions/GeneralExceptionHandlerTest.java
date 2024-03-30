package com.github.Rharhuky.api.resourcers.exceptions;

import com.github.Rharhuky.api.service.exceptions.InfoNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class GeneralExceptionHandlerTest {

    public static final String INFO_NOT_FOUND = "Info not found";
    @InjectMocks
    private GeneralExceptionHandler generalExceptionHandler;



    @Test
    @DisplayName(value = "Handle INFO NOT FOUND exception")
    void handleInfoNotFound() {

        ResponseEntity<StandardError> responseEntity = generalExceptionHandler.handleInfoNotFound(
                new InfoNotFoundException(INFO_NOT_FOUND),
                new MockHttpServletRequest());

        assertNotNull(responseEntity);
        assertEquals(ResponseEntity.class, responseEntity.getClass());
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(404, responseEntity.getBody().getStatus());
        assertEquals(StandardError.class, responseEntity.getBody().getClass());
        assertEquals(INFO_NOT_FOUND, responseEntity.getBody().getDetails());
    }

    @Test
    void handleDataIntegratyViolationException() {
    }
}