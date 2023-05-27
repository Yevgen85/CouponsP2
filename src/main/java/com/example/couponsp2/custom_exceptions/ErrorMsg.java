package com.example.couponsp2.custom_exceptions;

import lombok.Getter;

@Getter
public enum ErrorMsg {

    COUPON_ID_EXIST("Coupon Id Exist"),
    NO_COUPONS("No Coupons"),
    COUPON_NOT_EXIST("Coupon Not Exist"),
    CANT_ADD_SAME_COUPON_TWICE("Can't Add Same Coupon Twice"),
    CANT_PURCHASE_SAME_COUPON_TWICE("Can't Purchase Same Coupon Twice"),
    COUPON_AMOUNT_ZERO("Coupon Amount = 0!"),
    COUPON_DATE_EXPIRED("Coupon Date Expired"),
    COMPANY_EXIST("Company Exists"),
    COMPANY_ID_EXISTS("Company Id Exists"),
    COMPANY_NAME_EXISTS("Company Name Exists"),
    COMPANY_EMAIL_EXISTS("Company Email Exists"),
    CAN_NOT_UPDATE_COMPANY("Can't Update! Name And Id Must Be Same!"),
    NO_COMPANY_TO_DELETE("No Such Company to Delete"),
    NO_COMPANY("No Such Company"),
    CUSTOMER_EXISTS("Customer Exists!"),
    CUSTOMER_EMAIL_EXISTS("Customer Email Exists!"),
    CUSTOMER_ID_EXISTS("Customer ID Exists!"),
    NO_CUSTOMER_TO_UPDATE("There is No Such Customer To Update"),
    NO_CUSTOMER_TO_DELETE("There is No Such Customer To Delete"),
    NO_CUSTOMER_EXIST("No Customer Exist With This ID"),
    NO_CUSTOMERS("No Customers"),
    NO_COMPANIES("No Companies"),
    COUPON_WITH_THIS_TITLE_ALREADY_EXISTS("Coupon with this title already exists"),
    NO_SUCH_COUPON_TO_UPDATE("No Such Coupon To Update"),
    NO_SUCH_COUPON_TO_DELETE("No Such Coupon To Delete"),
    LOGGED_ID_ERROR("Logged Id Is Not As In The Path"),
    NOT_AUTHORIZED("Not Authorized for This Kind Of Activity!"),
    OLD_PASSWORD_INCORRECT("Old Password Incorrect"),
    ID_ERROR("Id Error! Id vs Email Mismatch"),
    BAD_CREDENTIALS("Email, Password Or Client Type Are Incorrect");

    private final String error;

    ErrorMsg(String error) {
        this.error = error;
    }

}
