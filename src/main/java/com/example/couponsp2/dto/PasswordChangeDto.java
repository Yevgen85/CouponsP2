package com.example.couponsp2.dto;

import com.example.couponsp2.beans.ClientType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PasswordChangeDto {

    private int id;
    private String oldPassword;
    private String newPassword;
}
