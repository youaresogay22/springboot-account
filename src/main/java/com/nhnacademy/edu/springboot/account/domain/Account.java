package com.nhnacademy.edu.springboot.account.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class Account {
    @Id
    private Long id;
    private String number;
    private Integer balance;

    public Account(Long id, String number, Integer balance) {
        this.id = id;
        this.number = number;
        this.balance = balance;
    }
}
