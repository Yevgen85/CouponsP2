package com.example.couponsp2.beans;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;


public enum ClientType {

    ADMINISTRATOR,
    COMPANY,
    CUSTOMER


}
