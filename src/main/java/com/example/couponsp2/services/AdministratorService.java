package com.example.couponsp2.services;

import com.example.couponsp2.beans.Administrator;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.sql.SQLException;

@Component
public class AdministratorService {

    /**
     * This method is checking if the email and the password of an admin are correct.
     * returns true or false
     */
    public boolean login(String email, String password) {
        return email.equals(Administrator.EMAIL) && password.equals(Administrator.PASSWORD);
    }
}
