package com.nhnacademy.edu.springboot.account.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.stream.Collectors;

@ResponseStatus(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE)
public class ValidationFailedException extends RuntimeException {
    public ValidationFailedException(BindingResult bindingResult) {
        super(bindingResult.getAllErrors()
                .stream()
                .map(error -> new StringBuilder().append("ObjectName=").append(error.getObjectName())
                        .append(",Message=").append(error.getDefaultMessage())
                        .append(",code=").append(error.getCode()))
                .collect(Collectors.joining(" | ")));
    }
}
