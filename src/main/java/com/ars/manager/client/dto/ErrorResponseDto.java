package com.ars.manager.client.dto;

import lombok.Data;

import java.util.List;

@Data
public class ErrorResponseDto {
    private String errorCode;
    private List<String> errorMessages;
}
