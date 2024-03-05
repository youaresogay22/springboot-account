package com.nhnacademy.edu.springboot.account.controller;

import com.nhnacademy.edu.springboot.account.domain.Account;
import com.nhnacademy.edu.springboot.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AccountWebController {
    AccountService accountService;

    @Autowired
    public AccountWebController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/web/accounts/{id}")
    public String getAccount(@PathVariable Long id,
                             Model model) {
        model.addAttribute("account", new Account(1L, "123", 1_000));
        return "account";
    }

    @GetMapping("/web/accounts")
    public String getAccount(Model model) {
        model.addAttribute("account", new Account(1L, accountService, 1_000));
        return "account";
    }
}
