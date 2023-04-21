package com.example.couponsp2.validators;

import com.example.couponsp2.beans.ClientType;
import com.example.couponsp2.beans.LoggedClientType;
import com.example.couponsp2.custom_exceptions.AuthorizationException;
import com.example.couponsp2.custom_exceptions.ErrorMsg;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthorizationValidator {

    private final LoggedClientType loggedClientType;


    public void validateAdmin() throws AuthorizationException {
        System.out.println(loggedClientType.getClientType() + ClientType.ADMINISTRATOR.name());
        if (!loggedClientType.getClientType().equals(ClientType.ADMINISTRATOR))
            throw new AuthorizationException(ErrorMsg.NOT_AUTHORIZED);
    }

    public void validateCompany() throws AuthorizationException {
        if (!loggedClientType.getClientType().equals(ClientType.COMPANY))
            throw new AuthorizationException(ErrorMsg.NOT_AUTHORIZED);
    }

    public void validateCustomer() throws AuthorizationException {
        if (!loggedClientType.getClientType().equals(ClientType.CUSTOMER))
            throw new AuthorizationException(ErrorMsg.NOT_AUTHORIZED);
    }

    public void validateCompanyOrCustomer() throws AuthorizationException {
        if (!loggedClientType.getClientType().equals(ClientType.COMPANY) && !loggedClientType.getClientType().equals(ClientType.CUSTOMER))
            throw new AuthorizationException(ErrorMsg.NOT_AUTHORIZED);
    }

    public void validateAdminOrCompany() throws AuthorizationException {
        if (!loggedClientType.getClientType().equals(ClientType.COMPANY) && !loggedClientType.getClientType().equals(ClientType.ADMINISTRATOR))
            throw new AuthorizationException(ErrorMsg.NOT_AUTHORIZED);
    }

    public void validateAdminOrCustomer() throws AuthorizationException {
        if (!loggedClientType.getClientType().equals(ClientType.CUSTOMER) && !loggedClientType.getClientType().equals(ClientType.ADMINISTRATOR))
            throw new AuthorizationException(ErrorMsg.NOT_AUTHORIZED);
    }
}
