package com.example.couponsp2.config;

import com.example.couponsp2.Token.TokenConfig;
import com.example.couponsp2.beans.ClientType;
import com.example.couponsp2.beans.LoggedClientType;
import com.example.couponsp2.services.*;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenConfig tokenConfig;
    private final UserService userService;
    private final LoggedClientType loggedClientType = new LoggedClientType();


    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenHeader = request.getHeader("Authorization");
        if (tokenHeader == null || !tokenHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        final String token = tokenHeader.substring(7);
        String userName = this.tokenConfig.getUserNameFromToken(token);
        String clientType = this.tokenConfig.getClientTypeFromToken(token);
        this.tokenConfig.getIdFromToken(token);

        if (!clientType.equals(ClientType.ADMINISTRATOR.toString()) && request.getRequestURI().contains("admin")) {
            throw new Exception("Error!!");
        }

        if (userName != null) {
            boolean isTokenExpirationValid = this.tokenConfig.isExpirationToken(token);
            if (isTokenExpirationValid) {
                        UserDetails userDetails = userService.loadUserByUsername(userName);
                        if (userDetails != null) {
                        UsernamePasswordAuthenticationToken authentication
                                = new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                new ArrayList<>());

                        SecurityContextHolder.getContext().setAuthentication(authentication);

                        }

            }
        }

        filterChain.doFilter(request, response);
    }
}
