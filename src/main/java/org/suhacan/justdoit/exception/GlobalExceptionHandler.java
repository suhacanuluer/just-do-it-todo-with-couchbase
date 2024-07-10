package org.suhacan.justdoit.exception;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.util.Date;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            UsernameAlreadyExistException.class,
    })
    @ResponseStatus(CONFLICT)
    ResponseEntity<ExceptionModel> handleCustomConflictException(UsernameAlreadyExistException ex, HttpServletRequest request) {
        ExceptionModel response = exceptionDetails(ex.getMessage(), ex, CONFLICT, request);
        return ResponseEntity
                .status(CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @ExceptionHandler({
            IllegalArgumentException.class,
            TaskNotFoundException.class,
            UsernameNotFoundException.class,
            JWTCreationException.class,
            JWTVerificationException.class,
            ConstraintViolationException.class
    })
    @ResponseStatus(BAD_REQUEST)
    ResponseEntity<ExceptionModel> handleCustomBadRequestException(Exception ex, HttpServletRequest request) {
        ExceptionModel response = exceptionDetails(ex.getMessage(), ex, BAD_REQUEST, request);
        return ResponseEntity
                .status(BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @ExceptionHandler({
            ServletException.class,
            NullPointerException.class,
            IOException.class
    })
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    ResponseEntity<ExceptionModel> handleCustomValidationException(Exception ex, HttpServletRequest request) {
        ExceptionModel response = exceptionDetails(ex.getMessage(), ex, INTERNAL_SERVER_ERROR, request);
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    private ExceptionModel exceptionDetails(String message, Exception exception, HttpStatus httpStatus, HttpServletRequest request) {
        var exceptionDetail = ExceptionModel.builder()
                .message(message)
                .status(httpStatus.value())
                .timestamp(new Date())
                .error(httpStatus.getReasonPhrase())
                .path(request.getRequestURI().substring(request.getContextPath().length())).build();

        log.error(exception.getMessage());
        return exceptionDetail;
    }
}
