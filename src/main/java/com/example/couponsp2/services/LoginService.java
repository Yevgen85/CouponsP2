//package com.example.couponsp2.services;
//
//import com.example.couponsp2.Token.TokenConfig;
//import com.example.couponsp2.beans.*;
//import com.example.couponsp2.dto.LoginRequestDTO;
//import com.example.couponsp2.dto.TokenResponseDTO;
//import com.example.couponsp2.repository.CompanyRepository;
//import com.example.couponsp2.repository.CustomerRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class LoginService implements UserDetailsService {
//
//
//
//    private final CompanyRepository companyRepository;
//    private final CustomerRepository customerRepository;
//    private final PasswordEncoder passwordEncoder;
//
//
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Company company = companyRepository.findByEmail(username);
//        Customer customer = customerRepository.findCustomerByEmail(username);
//        if (company != null) {
//            return new User(company.getUsername(), company.getPassword(), new ArrayList<>());
//        }
//        if (customer != null) {
//            return new User(customer.getUsername(), customer.getPassword(), new ArrayList<>());
//        }
//        if (username.contains("admin@admin.com")) {
//            return new User("admin@admin.com", passwordEncoder.encode("admin"), new ArrayList<>());
//        }
//        return null;
//    }
//}