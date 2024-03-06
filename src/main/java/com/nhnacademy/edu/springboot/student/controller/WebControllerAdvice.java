package com.nhnacademy.edu.springboot.student.controller;

import com.nhnacademy.edu.springboot.student.exception.StudentNotFoundException;
import com.nhnacademy.edu.springboot.student.exception.ValidationFailedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
@ResponseBody
public class WebControllerAdvice {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(StudentNotFoundException.class)
    public String handleNotFoundException(Exception ex, Model model) {
        log.error("", ex);

        model.addAttribute("exception", ex);

        return "error:" + ex;
    }

    @ResponseStatus(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE)
    @ExceptionHandler(ValidationFailedException.class)
    public String handleValidationFailedException(Exception ex, Model model) {
        log.error("", ex);

        model.addAttribute("exception", ex);
        return "error:" + ex;
    }

    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    @ExceptionHandler(Exception.class)
    public String handleUncaughtException(Exception ex, Model model) {
        log.error("", ex);

        model.addAttribute("exception", ex);
        return "error:" + ex;
    }


}
