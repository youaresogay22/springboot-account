package com.nhnacademy.edu.springboot.account.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties("my.account")
@Getter
@Setter
@ToString
public class AccountProperties {
    String number;
    int balance;

    @ConstructorBinding
    public AccountProperties(String number, int balance) {

        if (StringUtil.isEmpty(number)) {
            throw new IllegalArgumentException("number is null");
        }

        this.number = number;
        this.balance = balance;
    }
}
