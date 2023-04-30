package com.example.couponsp2.services;

import com.example.couponsp2.beans.*;
import com.example.couponsp2.custom_exceptions.AuthorizationException;
import com.example.couponsp2.custom_exceptions.CustomerException;
import com.example.couponsp2.dto.CustomerDTO;
import com.example.couponsp2.dto.PasswordChangeDto;
import com.example.couponsp2.repository.CustomerRepository;
import com.example.couponsp2.validators.AuthorizationValidator;
import com.example.couponsp2.validators.CustomerValidator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerValidator customerValidator;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    private final AuthorizationValidator authorizationValidator;
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
    public CustomerDTO addCustomer(Customer customer) throws CustomerException, AuthorizationException {
        authorizationValidator.validateAdmin();
        customerValidator.addValidator(customer);
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return convertToCustomerDTO(customerRepository.save(customer));
    }

    /**
     * This method updates customer
     * update can be done only if customer id is exists in the database
     */
    public CustomerDTO updateCustomer(int customerId, Customer customer) throws CustomerException, AuthorizationException {
        authorizationValidator.validateAdmin();
        customerValidator.updateValidator(customerId, customer);
        Customer customerToUpdate = customerRepository.findById(customer.getId()).orElse(null);
        customer.setPassword(customerToUpdate.getPassword());
        return convertToCustomerDTO(customerRepository.save(customer));
    }

    /**
     * This method is deletes company from a database
     * checks if company exists and then deletes
     * if not exists, message of not exist is printed
     */
    public void deleteCustomer(int customerId) throws CustomerException, AuthorizationException {
        authorizationValidator.validateAdmin();
        customerValidator.isExistValidator(customerId);
        customerRepository.deleteById(customerId);
    }

    /**
     * This method returns a list of all customers are in a database
     */
    public List<CustomerDTO> getAll() throws AuthorizationException {
        authorizationValidator.validateAdmin();
        return convertToListOfCustomersDTO(customerRepository.findAll());
    }

    /**
     * This method returns a customer by searching in the database by id
     */
    public CustomerDTO getOne(int customerId) throws CustomerException, AuthorizationException {
        authorizationValidator.validateAdminOrCustomer();
        customerValidator.isExistValidator(customerId);
        return convertToCustomerDTO(customerRepository.findById(customerId).get());
    }

    public CustomerDTO getOne(String email) throws CustomerException {
        customerValidator.isEmailExistValidator(email);
        return convertToCustomerDTO(customerRepository.findCustomerByEmail(email));
    }

    public Customer findByEmail(String username) {
        return customerRepository.findCustomerByEmail(username);
    }

    private CustomerDTO convertToCustomerDTO(Customer customer) {
        return this.modelMapper.map(customer, CustomerDTO.class);
    }

    private List<CustomerDTO> convertToListOfCustomersDTO(List<Customer> customers) {
        return customers.stream().map(this::convertToCustomerDTO).collect(Collectors.toList());
    }

    public Map<String, Object> buildClaims(Customer customer) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", customer.getUsername());
        claims.put("firstName", customer.getFirstName());
        claims.put("lastName", customer.getLastName());
        claims.put("id", customer.getId());
        return claims;
    }

    public Customer getOneForCouponPurchase(int customerId) throws CustomerException, AuthorizationException {
//        authorizationValidator.validateCustomer();
        customerValidator.isExistValidator(customerId);
        return customerRepository.findById(customerId).get();
    }

    public void changePassword(int customerId, PasswordChangeDto passwordChangeDTO) throws CustomerException {
        customerValidator.passwordValidator(customerId, passwordChangeDTO);
        Customer customer = customerRepository.findById(customerId).get();
        customer.setPassword(passwordEncoder.encode(passwordChangeDTO.getNewPassword()));
        customerRepository.save(customer);
    }
}
