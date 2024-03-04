package com.nhnacademy.edu.springboot.account.service;

import com.nhnacademy.edu.springboot.account.domain.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DefaultAccountServiceTest {
    @Autowired
    private AccountService accountService;

    @Test
    void getAccounts_test() {
        List<Account> list = accountService.getAccounts();
        Assertions.assertEquals(2, list.size());
    }

}