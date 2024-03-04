package com.nhnacademy.edu.springboot.account.repository;

import com.nhnacademy.edu.springboot.account.domain.Account;

import java.util.List;

public interface AccountRepository {
    List<Account> findAll();
}
