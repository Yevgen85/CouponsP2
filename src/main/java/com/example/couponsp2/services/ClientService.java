package com.example.couponsp2.services;

import com.example.couponsp2.beans.Administrator;
import com.example.couponsp2.beans.ClientType;
import com.example.couponsp2.repository.CompanyRepository;
import com.example.couponsp2.repository.CouponRepository;
import com.example.couponsp2.repository.CustomerRepository;
import com.example.couponsp2.repository.CustomersVsCouponsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

//@Component
@RequiredArgsConstructor
public abstract class ClientService {


    protected final CompanyRepository companyRepository;
    protected final CustomerRepository customerRepository;
    protected final CouponRepository couponRepository;
    protected final CustomersVsCouponsRepository customersVsCouponsRepository;



    public boolean loginCS(String email, String password) throws SQLException {
        return  email.equals(Administrator.EMAIL) && password.equals(Administrator.PASSWORD) || customerRepository.existsByEmailAndPassword(email, password) || companyRepository.existsByEmailAndPassword(email, password);
    }
}
