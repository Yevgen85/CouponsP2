package com.example.couponsp2.custom_exceptions;

public class CustomerException extends Exception{
    public CustomerException(ErrorMsg errorMsg) {
        super(errorMsg.getError());
    }
}
