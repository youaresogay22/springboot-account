package com.nhnacademy.edu.springboot.account.repository;


import com.nhnacademy.edu.springboot.account.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
