package com.example.EmployeeOrg.advice;

import com.example.EmployeeOrg.exceptions.ResourceNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleResource(ResourceNotFoundException exception){
       ApiErrors apiErrors = ApiErrors.builder().status(HttpStatus.NOT_FOUND).message(exception.getMessage()).build();
       return builderErrorEntity(apiErrors);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleException(Exception exception){
        ApiErrors apiErrors = ApiErrors.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).message(exception.getMessage()).build();
        return builderErrorEntity(apiErrors);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleInputValidationsError(MethodArgumentNotValidException exception){
        List<String> errors = exception.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        ApiErrors apiErrors = ApiErrors.builder().status(HttpStatus.BAD_REQUEST).message("Input validation failed").subErrors(errors).build();
        return builderErrorEntity(apiErrors);
    }

    private ResponseEntity<ApiResponse<?>> builderErrorEntity(ApiErrors apiErrors) {
        return new ResponseEntity<>(new ApiResponse<>(apiErrors), apiErrors.getStatus());
    }
}
