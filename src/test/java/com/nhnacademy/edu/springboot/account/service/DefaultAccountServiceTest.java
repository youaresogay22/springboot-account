package com.nhnacademy.edu.springboot.account.service;


import com.nhnacademy.edu.springboot.account.domain.Account;
import com.nhnacademy.edu.springboot.account.repository.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class DefaultAccountServiceTest {
    @InjectMocks
    private DefaultAccountService accountService;
    @Mock
    private AccountRepository accountRepository;

    @Test
    void getAccounts_test() {
        when(accountRepository.findAll()).thenReturn(List.of(new Account(), new Account()));

        List<Account> list = accountService.getAccounts();
        Assertions.assertEquals(2, list.size());
    }

}