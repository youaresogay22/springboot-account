package com.nhnacademy.edu.springboot.account.serverproperties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("customer")
@Component
public class CustomerServerProperties implements ServerProperties {
    @Value("${my.openapi.port}")
    String port;

    @Override
    public String getPort() {
        return port;
    }
}
