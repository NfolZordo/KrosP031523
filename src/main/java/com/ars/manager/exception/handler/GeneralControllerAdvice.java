package com.ars.manager.exception.handler;


import com.ars.manager.exception.NotFoundException;
import org.openapitools.model.ErrorDto;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class GeneralControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    public ErrorDto handle(NotFoundException ex) {
        return new ErrorDto()
                .code(NOT_FOUND.value())
                .message(ex.getMessage());
    }
}
