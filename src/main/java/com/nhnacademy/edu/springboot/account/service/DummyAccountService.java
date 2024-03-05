package com.nhnacademy.edu.springboot.account.service;

import com.nhnacademy.edu.springboot.account.domain.Account;
import com.nhnacademy.edu.springboot.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Profile("!prod")
@Service
public class DummyAccountService implements AccountService {
    private final AccountRepository accountRepository;

    @Value("${number}")
    private String num;

    @Autowired
    public DummyAccountService(AccountRepository accountRepository) {
        System.out.println("-----Dummy-----");
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> getAccounts() {
        return null;
    }

    @Override
    public Account getAccount(Long id) {
        return null;
    }

    @Override
    public Account createAccount(Account account) {
        return null;
    }

    @Override
    public void deleteAccount(Long id) {

    }

    @Override
    public String getNum() {
        return num;
    }
}
