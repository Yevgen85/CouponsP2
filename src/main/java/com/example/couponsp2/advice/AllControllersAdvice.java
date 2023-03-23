package com.example.couponsp2.advice;

import com.example.couponsp2.custom_exceptions.CompanyException;
import com.example.couponsp2.custom_exceptions.CouponException;
import com.example.couponsp2.custom_exceptions.CustomerException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AllControllersAdvice {

    @ResponseBody
    @ExceptionHandler(value = {CompanyException.class, CouponException.class, CustomerException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorObj handleError(Exception e) {
        return new ErrorObj("Error", e.getMessage());
    }
}
