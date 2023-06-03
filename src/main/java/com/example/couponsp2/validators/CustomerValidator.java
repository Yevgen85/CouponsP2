package com.example.couponsp2.validators;

import com.example.couponsp2.beans.Company;
import com.example.couponsp2.beans.Customer;
import com.example.couponsp2.custom_exceptions.CompanyException;
import com.example.couponsp2.custom_exceptions.CustomerException;
import com.example.couponsp2.custom_exceptions.ErrorMsg;
import com.example.couponsp2.dto.PasswordChangeDto;
import com.example.couponsp2.repository.CustomerRepository;
import com.example.couponsp2.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerValidator {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public void addValidator(Customer customer) throws  CustomerException {
        if (customerRepository.existsById(customer.getId())) {
            throw new CustomerException(ErrorMsg.CUSTOMER_ID_EXISTS);
        }
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new CustomerException(ErrorMsg.CUSTOMER_EMAIL_EXISTS);
        }
    }

    public void updateValidator(int customerId, Customer customer) throws CustomerException {
        if (!customerRepository.existsById(customer.getId())) {
            throw new CustomerException(ErrorMsg.NO_CUSTOMER_TO_UPDATE);
        }
        if (customer.getId() != customerId) {
            throw new CustomerException(ErrorMsg.ID_ERROR);
        }
        if (customerRepository.existsByEmailAndIdIsNot(customer.getEmail(), customerId)) {
            throw new CustomerException(ErrorMsg.CUSTOMER_EMAIL_EXISTS);
        }
    }

        public void isExistValidator(int customerId) throws CustomerException {
            if (!customerRepository.existsById(customerId)) {
                throw new CustomerException(ErrorMsg.NO_CUSTOMER_EXIST);
            }
        }

    public void isEmailExistValidator(String email) throws CustomerException {
        if (!customerRepository.existsByEmail(email)) {
            throw new CustomerException(ErrorMsg.NO_CUSTOMER_EXIST);
        }
    }

    public void passwordValidator(int customerId, PasswordChangeDto passwordChangeDto) throws CustomerException {
        isExistValidator(customerId);
        if (customerId != passwordChangeDto.getId()) {
            throw new CustomerException(ErrorMsg.LOGGED_ID_ERROR);
        }
        Customer customer = customerRepository.findById(customerId).get();
        if (!passwordEncoder.matches(passwordChangeDto.getOldPassword(), customer.getPassword())) {
            throw new CustomerException(ErrorMsg.OLD_PASSWORD_INCORRECT);
        }
    }
    }