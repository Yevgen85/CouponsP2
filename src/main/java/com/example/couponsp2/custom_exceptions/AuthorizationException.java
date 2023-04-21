package com.example.couponsp2.custom_exceptions;

public class AuthorizationException extends Exception {
    public AuthorizationException(ErrorMsg errorMsg) {
        super(errorMsg.getError());
    }
}
