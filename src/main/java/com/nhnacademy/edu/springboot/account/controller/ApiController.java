package com.nhnacademy.edu.springboot.account.controller;

import com.nhnacademy.edu.springboot.account.domain.AccountIdDTO;
import com.nhnacademy.edu.springboot.account.entity.Account;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ConfigurationProperties(prefix = "my.openapi.port")
public class ApiController {
    private String account;
    private String customer;

    @GetMapping("/accounts")
    public List<Account> getAccounts() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<Account>> exchange = restTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                accountlist);

        if (exchange.getStatusCode() != HttpStatus.OK)
            throw new RuntimeException();

        return exchange.getBody();
        return accountService.getAccounts();
    }

    @GetMapping("/accounts/{id}")
    public Account getAccount(@PathVariable Long id) {
        return accountService.getAccount(id);
    }

    @PostMapping("/accounts")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AccountIdDTO> createAccount(@RequestBody Account account) {
        try {
            accountService.createAccount(account);
        } catch (RuntimeException e) {
            ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.getAccountIdDTO(account));
    }

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {

        if (accountService.exists(id)) {
            try {
                accountService.deleteAccount(id);
            } catch (RuntimeException e) {
                return ResponseEntity.internalServerError().build();
            }
            accountService.deleteAccount(id);
            return ResponseEntity.ok().build();
        } else
            return ResponseEntity.notFound().build();

    }
}
