package com.nhnacademy.edu.springboot.account;

import com.nhnacademy.edu.springboot.account.domain.AccountProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@ConfigurationPropertiesScan
public class AccountApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctxt = SpringApplication.run(AccountApplication.class, args);
        AccountProperties accountProperties = ctxt.getBean(AccountProperties.class);
        System.out.println(accountProperties.toString());
    }

}
