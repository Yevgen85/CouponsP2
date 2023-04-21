//package com.example.couponsp2.services;
//
//import com.example.couponsp2.Token.TokenConfig;
//import com.example.couponsp2.dto.LoginRequestDTO;
//import com.example.couponsp2.dto.TokenResponseDTO;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class LoginValidation {
//
//    private final AuthenticationManager authenticationManager;
//    private final TokenConfig tokenConfig;
//    private final LoginService loginService;
//
//    public TokenResponseDTO validateLoginDetails(LoginRequestDTO loginRequestDTO) {
//        boolean isLoginDetailsValid = this.isLoginDetailsValid(loginRequestDTO);
//        if (isLoginDetailsValid) {
//
//            User user = (User) loginService.loadUserByUsername(loginRequestDTO.getUsername());
//            String userToken = this.tokenConfig.generateToken(this.tokenConfig
//                    .buildClaims(user));
//
//            TokenResponseDTO token = new TokenResponseDTO(userToken);
//            System.out.println(token);
//            return token;
//        }
//        return null;
//    }
//
//
//    private boolean isLoginDetailsValid(LoginRequestDTO loginRequestDTO) {
//        System.out.println(loginRequestDTO.getUsername());
//        try {
//            this.authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            loginRequestDTO.getUsername(),
//                            loginRequestDTO.getPassword()
//                    )
//            );
//        }
//        catch (Exception e) {
//           throw new RuntimeException("Bad Login or Password");
//        }
//
//        return true;
//    }
//
//}
