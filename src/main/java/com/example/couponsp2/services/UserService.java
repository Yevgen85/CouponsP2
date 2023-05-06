package com.example.couponsp2.services;

import com.example.couponsp2.Token.TokenConfig;
import com.example.couponsp2.beans.Company;
import com.example.couponsp2.beans.Customer;
import com.example.couponsp2.custom_exceptions.AuthorizationException;
import com.example.couponsp2.custom_exceptions.ErrorMsg;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {

    private final CompanyService companyService;
    private final CustomerService customerService;
//    private final TokenConfig tokenConfig;
    private final PasswordEncoder passwordEncoder;

    public UserService(CompanyService companyService,
                       TokenConfig tokenConfig,
                       @Lazy PasswordEncoder passwordEncoder,
                       CustomerService customerService) {
        this.companyService = companyService;
//        this.tokenConfig = tokenConfig;
        this.passwordEncoder = passwordEncoder;
        this.customerService = customerService;
    }


//    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals("admin@admin.com")) {
            return new User("admin@admin.com", this.passwordEncoder.encode("admin"), new ArrayList<>());
        }
        Company company = (Company) this.companyService.findByEmail(username);
        if (company != null) {
            return company;
        }
        Customer customer = this.customerService.findByEmail(username);
        if (customer != null) {
            return this.customerService.findByEmail(username);
        }
        try {
            throw new AuthorizationException(ErrorMsg.BAD_CREDENTIALS);
        } catch (AuthorizationException e) {
            throw new RuntimeException(e);
        }
    }
}
