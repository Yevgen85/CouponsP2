package com.example.couponsp2.controller;

import com.example.couponsp2.beans.Customer;
import com.example.couponsp2.custom_exceptions.CustomerException;
import com.example.couponsp2.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("customers/{id}")
    public Customer getSingleCustomer(@PathVariable int id) throws SQLException, CustomerException {
        return customerService.getOne(id);
    }

}
