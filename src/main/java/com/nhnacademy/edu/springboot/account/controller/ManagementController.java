package com.nhnacademy.edu.springboot.account.controller;

import com.nhnacademy.edu.springboot.account.actuator.MyHealthIndicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManagementController {
    private final MyHealthIndicator myHealthIndicator;

    @Autowired
    public ManagementController(MyHealthIndicator myHealthIndicator) {
        this.myHealthIndicator = myHealthIndicator;
    }

    @PostMapping("/management/fail")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void setStatusToDown() {
        myHealthIndicator.updateStop();
    }

    @PostMapping("/management/running")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void setStatusToUp() {
        myHealthIndicator.updateRunning();
    }

}
