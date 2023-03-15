package com.ars.manager.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class ClientValidationException extends Exception {

    private final String errorCode;
    private final List<String> errorMessages;

    public ClientValidationException(String errorCode, List<String> errorMessages) {
        this.errorCode = errorCode;
        this.errorMessages = errorMessages;
    }
}
