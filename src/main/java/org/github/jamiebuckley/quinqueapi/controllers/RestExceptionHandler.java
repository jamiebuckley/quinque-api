package org.github.jamiebuckley.quinqueapi.controllers;

import jakarta.persistence.EntityNotFoundException;
import org.github.jamiebuckley.quinqueapi.dto.APIError;
import org.github.jamiebuckley.quinqueapi.exceptions.NoMoreGuessesAllowedException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
        APIError apiError = new APIError(NOT_FOUND, ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(NoMoreGuessesAllowedException.class)
    protected ResponseEntity<Object> handleNoMoreGuessses(NoMoreGuessesAllowedException ex) {
        APIError apiError = new APIError(BAD_REQUEST, "No more guesses allowed on game %s".formatted(ex.getGameId()));
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(APIError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
