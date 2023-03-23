package com.example.couponsp2.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;
@AllArgsConstructor

@Data
@ToString
@Component
public class Administrator {

    public static final String EMAIL = "admin@admin.com";
    public static final String PASSWORD = "admin";

}
