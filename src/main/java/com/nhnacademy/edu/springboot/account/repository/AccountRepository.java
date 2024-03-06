package com.nhnacademy.edu.springboot.account.repository;


import com.nhnacademy.edu.springboot.account.domain.AccountIdDTO;
import com.nhnacademy.edu.springboot.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    AccountIdDTO findAccountById(Long id);
}
