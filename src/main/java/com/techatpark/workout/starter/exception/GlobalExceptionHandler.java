package com.techatpark.workout.starter.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles ConstraintViolationException.
     * @param e
     * @return errorResponse
     */
    @ExceptionHandler(ConstraintViolationException.class)
    ErrorResponse handleConstraintViolationException(
            final ConstraintViolationException e) {
        return ErrorResponse.builder(e,
                        HttpStatus.BAD_REQUEST,
                        e.getMessage())
                .title(e.getMessage())
                .detail(e.getMessage())
                .detailMessageCode(e.getMessage())
                .detailMessageArguments(e.getConstraintViolations().toArray())
                .type(URI
                    .create("https://api.gurukulams.com/errors/bad_request"))
                .property("errorCategory", "Generic")
                .property("timestamp", Instant.now())
                .build();
    }


}
