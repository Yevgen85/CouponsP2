package com.example.couponsp2.services;

import com.example.couponsp2.beans.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final AdministratorService administratorService;
    private final CompanyService companyService;
    private final CustomerService customerService;



    public boolean login(String email, String password, ClientType clientType) throws SQLException {

        switch (clientType) {
            case ADMINISTRATOR:
                if (administratorService.login(email, password)) {
                    return true;
                } else {
                    System.out.println("Admin Login Error");
                    return false;
                }

            case COMPANY:
                if (companyService.login(email, password)) {
                    return true;
                } else {
                    System.out.println("Company Login Error");
                    return false;
                }

            case CUSTOMER:
                if (customerService.login(email, password)) {
                    return true;
                } else {
                    System.out.println("Customer Login Error");
                    return false;
                }

            default:
                return false;
        }
    }
}