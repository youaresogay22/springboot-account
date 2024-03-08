package com.nhnacademy.edu.springboot.account.domain;

import com.nhnacademy.edu.springboot.account.entity.Customer;
import lombok.Data;

@Data
public class OpenApiResponseDTO {
    private Long id;
    private String number;
    private Integer balance;
    private Customer customer;
}
