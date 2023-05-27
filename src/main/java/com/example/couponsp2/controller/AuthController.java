package com.example.couponsp2.controller;

import com.example.couponsp2.custom_exceptions.AuthorizationException;
import com.example.couponsp2.dto.LoginRequestDTO;
import com.example.couponsp2.dto.TokenResponseDTO;
import com.example.couponsp2.services.AuthService;
import com.example.couponsp2.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth/")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public TokenResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO) throws Exception {
        return authService.validateLoginDetails(loginRequestDTO);
    }
}
