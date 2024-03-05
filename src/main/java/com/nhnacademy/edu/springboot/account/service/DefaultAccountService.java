package com.nhnacademy.edu.springboot.account.service;

import com.nhnacademy.edu.springboot.account.domain.Account;
import com.nhnacademy.edu.springboot.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Profile("prod")
@Service
public class DefaultAccountService implements AccountService {
    private final AccountRepository accountRepository;

    @Value("${number}")
    private String num;

    @Autowired
    public DefaultAccountService(AccountRepository accountRepository) {
        System.out.println("-----Default-----");
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Account getAccount(Long id) {
        return accountRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public Account createAccount(Account account) {
        boolean present = accountRepository.findById(account.getId()).isPresent();
        if (present) throw new IllegalStateException("already exist " + account.getId());

        return accountRepository.save(account);
    }

    @Override
    @Transactional
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public String getNum() {
        return num;
    }
}
