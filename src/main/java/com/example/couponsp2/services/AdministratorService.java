package com.example.couponsp2.services;

import com.example.couponsp2.Token.TokenConfig;
import com.example.couponsp2.beans.Administrator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdministratorService {

    /**
     * This method is checking if the email and the password of an admin are correct.
     * returns true or false
     */
    public boolean login(String email, String password) {
        return email.equals(Administrator.EMAIL) && password.equals(Administrator.PASSWORD);
    }

    public Map<String, Object> buildClaims(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        return claims;
    }
}
