package com.example.couponsp2.dto;

import com.example.couponsp2.beans.ClientType;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PasswordChangeDto {

    private int id;
    @Size(min = 6, max = 100, message = "Password must be between 6 to 100 symbols length")
    private String oldPassword;
    @Size(min = 6, max = 100, message = "Password must be between 6 to 100 symbols length")
    private String newPassword;
}
