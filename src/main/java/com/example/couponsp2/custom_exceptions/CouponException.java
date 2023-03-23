package com.example.couponsp2.custom_exceptions;

public class CouponException extends Exception{
    public CouponException(ErrorMsg errorMsg) {
        super(errorMsg.getError());
    }
}
