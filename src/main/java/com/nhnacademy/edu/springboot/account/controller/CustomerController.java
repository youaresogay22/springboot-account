package com.nhnacademy.edu.springboot.account.controller;


import com.nhnacademy.edu.springboot.account.entity.Customer;
import com.nhnacademy.edu.springboot.account.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customer/{id}")
    public Customer getCustomer(@PathVariable Long id) {
        return customerService.getCustomer(id);
    }

    @PostMapping("/customer")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
        try {
            customerService.createCustomer(customer);
        } catch (RuntimeException e) {
            ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("created : " + customer.getName());
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {

        if (customerService.exists(id)) {
            try {
                customerService.deleteCustomer(id);
            } catch (RuntimeException e) {
                return ResponseEntity.internalServerError().build();
            }
            customerService.deleteCustomer(id);
            return ResponseEntity.ok().build();
        } else
            return ResponseEntity.notFound().build();

    }

}
