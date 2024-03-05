package com.nhnacademy.edu.springboot.account.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component("myHealth")
public class MyHealthIndicator implements HealthIndicator {

    private Boolean isAlive = true;

    public void updateRunning() {
        isAlive = true;
    }

    public void updateStop() {
        isAlive = false;
    }

    @Override
    public Health health() {
        if (isAlive = true) {
            return Health.up().withDetail("maintenance", "false").build();
            
        } else return Health.down().withDetail("maintenance", "true").build();
    }

    private int check() {
        // perform some specific health check
        return 0;
    }

}
