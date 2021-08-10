package com.gl.registry.exeptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class WebExceptionHandler {

    private static final String RESPONSE_CODE_PATTERN = "%s.%s.%s";

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = NOT_FOUND)
    public ExceptionResponse notFoundException(EntityNotFoundException ex) {
        String exceptionMessage = String.format(RESPONSE_CODE_PATTERN, ex.getClass().getSimpleName(), ex.getFieldName(), NOT_FOUND.value());
        return new ExceptionResponse(exceptionMessage, ex.getMessage());
    }
}
