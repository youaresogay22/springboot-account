package com.nhnacademy.edu.springboot.account.repository;

import com.nhnacademy.edu.springboot.account.domain.Account;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
@Transactional
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void AccountRepository_test() {
        //given
        Account testAccount = new Account(1L, "123", 1_000);
        accountRepository.save(testAccount);
        //when
        Optional<Account> actual = accountRepository.findById(1L);
        //then
        Assertions.assertThat(actual).isPresent();
        Assertions.assertThat(actual.get()).isEqualTo(testAccount);
    }
}