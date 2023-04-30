package com.example.couponsp2.beans;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Data
public class LoggedClientType {
    private ClientType clientType;
    private int id;
}
