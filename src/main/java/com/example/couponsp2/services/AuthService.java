package com.example.couponsp2.services;


import com.example.couponsp2.Token.TokenConfig;
import com.example.couponsp2.beans.Company;
import com.example.couponsp2.beans.Customer;
import com.example.couponsp2.custom_exceptions.AuthorizationException;
import com.example.couponsp2.custom_exceptions.ErrorMsg;
import com.example.couponsp2.dto.LoginRequestDTO;
import com.example.couponsp2.dto.TokenResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final TokenConfig tokenConfig;
    private final UserService userService;
    private final CompanyService companyService;
    private final AdministratorService adminService;
    private final CustomerService customerService;

    public TokenResponseDTO validateLoginDetails(LoginRequestDTO loginRequestDTO) throws AuthorizationException {
        boolean isLoginDetailsValid = this.isLoginDetailsValid(loginRequestDTO);
        if (isLoginDetailsValid) {
            UserDetails userDetails = this.userService.loadUserByUsername(loginRequestDTO.getUsername());
            Map<String, Object> claims = null;
            switch (loginRequestDTO.getClientType()) {
                case ADMINISTRATOR -> claims = this.adminService.buildClaims((User) userDetails);
                case COMPANY -> claims = this.companyService.buildClaims((Company) userDetails);
                case CUSTOMER -> claims = this.customerService.buildClaims((Customer) userDetails);
            }
            claims.put("clientType", loginRequestDTO.getClientType());
            String token = this.tokenConfig.generateToken(claims);
            return new TokenResponseDTO(token);
        }
        throw new AuthorizationException(ErrorMsg.BAD_CREDENTIALS);
    }

    private boolean isLoginDetailsValid(LoginRequestDTO loginRequestDTO) {
        System.out.println(loginRequestDTO.getUsername());
        try {
            this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDTO.getUsername(),
                            loginRequestDTO.getPassword()
                    )
            );
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

}
