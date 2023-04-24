package com.example.couponsp2.controller;

import com.example.couponsp2.beans.Company;
import com.example.couponsp2.beans.Customer;
import com.example.couponsp2.custom_exceptions.AuthorizationException;
import com.example.couponsp2.custom_exceptions.CompanyException;
import com.example.couponsp2.custom_exceptions.CustomerException;
import com.example.couponsp2.dto.CompanyDTO;
import com.example.couponsp2.dto.CustomerDTO;
import com.example.couponsp2.repository.CustomerRepository;
import com.example.couponsp2.services.CustomerService;
import com.example.couponsp2.validators.AuthorizationValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/customer")
@CrossOrigin
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping()
    public List<CustomerDTO> getCustomers() throws AuthorizationException {
        return this.customerService.getAll();
    }

    @GetMapping("/{id}")
    public CustomerDTO getSingleCustomer(@PathVariable int id) throws CustomerException, AuthorizationException {
        return customerService.getOne(id);
    }

//    @GetMapping("/customer/{email}")
//    public CustomerDTO getSingleCustomer(@PathVariable String email) throws CustomerException {
//        return customerService.getOne(email);
//    }

    @DeleteMapping("/{id}")
    public void deleteCustomer (@PathVariable int id) throws CustomerException, AuthorizationException {
        customerService.deleteCustomer(id);
    }

    @PostMapping()
    public CustomerDTO addCustomer(@RequestBody Customer customer) throws CustomerException, AuthorizationException {
        return customerService.addCustomer(customer);
    }

    @PutMapping("/{id}")
    public CustomerDTO updateCustomer(@PathVariable int id, @RequestBody  Customer customer) throws CustomerException, AuthorizationException {
        System.out.println(id + " + " + customer);
        return customerService.updateCustomer(id, customer);
    }

//    @PostMapping("/filter")
//    public boolean isExist(@RequestParam String email, @RequestParam String password) {
//        return companyService.isExist(email, password);
//    }


}
