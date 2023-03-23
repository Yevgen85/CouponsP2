package com.example.couponsp2.services;

import com.example.couponsp2.beans.Category;
import com.example.couponsp2.beans.Coupon;
import com.example.couponsp2.beans.Customer;
import com.example.couponsp2.beans.CustomersVsCoupons;
import com.example.couponsp2.custom_exceptions.CompanyException;
import com.example.couponsp2.custom_exceptions.CustomerException;
import com.example.couponsp2.custom_exceptions.ErrorMsg;
import com.example.couponsp2.repository.CustomerRepository;
import com.example.couponsp2.validators.CustomerValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerValidator customerValidator;

    /**
     *This method is checking if the email and the password of a customer are correct.
     * returns true or false
     */
    public boolean login(String email, String password) throws SQLException {
        return customerRepository.existsByEmailAndPassword(email, password);
    }

    /**
     *This method is checking if customer is exists in the database
     * sending customer ID
     * return true or false
     */
    public boolean isCustomerExists(int customerId) throws SQLException {
        return customerRepository.existsById(customerId);
    }

    /**
     * This method adds a customer to the database
     * checks if there is a match of email and password before
     * if there was a match, no customer added because already exists
     */
    public void addCustomer(Customer customer) throws  CustomerException {
        customerValidator.addValidator(customer);
        customerRepository.save(customer);
    }

    /**
     * This method updates customer
     * update can be done only if customer id is exists in the database
     */
    public void updateCustomer(Customer customer) throws CustomerException {
        customerValidator.updateValidator(customer);
            customerRepository.save(customer);
    }

    /**
     * This method is deletes company from a database
     * checks if company exists and then deletes
     * if not exists, message of not exist is printed
     */
    public void deleteCustomer(int customerId) throws CustomerException {
        customerValidator.isExistValidator(customerId);
    }

    /**
     * This method returns a list of all customers are in a database
     */
    public List<Customer> getAll() throws SQLException {
        return customerRepository.findAll();
    }

    /**
     * This method returns a customer by searching in the database by id
     */
    public Customer getOne(int customerId) throws SQLException, CustomerException {
        customerValidator.isExistValidator(customerId);
        return customerRepository.findById(customerId).orElse(null);
    }
}
