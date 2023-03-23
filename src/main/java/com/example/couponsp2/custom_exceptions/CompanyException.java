package com.example.couponsp2.custom_exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class CompanyException extends Exception{
    public CompanyException(ErrorMsg errorMsg) {
        super(errorMsg.getError());
    }


}
