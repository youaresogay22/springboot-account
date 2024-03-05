package com.nhnacademy.edu.springboot.account.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Student not found on the server")
public class StudentNotFoundException extends RuntimeException {

}
