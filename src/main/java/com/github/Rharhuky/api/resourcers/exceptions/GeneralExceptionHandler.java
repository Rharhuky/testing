package com.github.Rharhuky.api.resourcers.exceptions;

import com.github.Rharhuky.api.service.exceptions.InfoNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InfoNotFoundException.class)
    public ResponseEntity<StandardError> handleInfoNotFound(InfoNotFoundException exception, HttpServletRequest webRequest){

        StandardError standardError = StandardError.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .path(webRequest.getRequestURI())
                .details(exception.getMessage())
                .build();

        return new ResponseEntity<>(standardError, HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler({RuntimeException.class})
//    public ResponseEntity<StandardError> standardErrorResponseEntity(RuntimeException exception, WebRequest request){
//        StandardError standardError;
//        ResponseStatus responseStatusAnnotation = AnnotationUtils.findAnnotation(exception.getClass(), ResponseStatus.class);
//        if(Objects.nonNull(responseStatusAnnotation)){
//            standardError = StandardError
//                    .builder()
//                    .status(responseStatusAnnotation.code().value())
//                    .details(responseStatusAnnotation.reason())
//                    .path(request.getDescription(false))
//                    .timestamp(LocalDateTime.now())
//                    .build();
//            return new ResponseEntity<>(standardError, responseStatusAnnotation.code());
//
//        }
//        standardError = StandardError
//                .builder()
//                .status(HttpStatus.NOT_FOUND.value())
//                .details(exception.getMessage())
//                .path(request.getDescription(false))
//                .timestamp(LocalDateTime.now())
//                .build();
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
//    }

}
