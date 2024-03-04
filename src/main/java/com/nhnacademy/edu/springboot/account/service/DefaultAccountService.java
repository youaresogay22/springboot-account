package com.nhnacademy.edu.springboot.account.service;

import com.nhnacademy.edu.springboot.account.domain.Account;
import com.nhnacademy.edu.springboot.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultAccountService implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public DefaultAccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }
}
