package com.example.couponsp2.validators;

import com.example.couponsp2.beans.Company;
import com.example.couponsp2.custom_exceptions.CompanyException;
import com.example.couponsp2.custom_exceptions.ErrorMsg;
import com.example.couponsp2.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompanyValidator {

    private final CompanyRepository companyRepo;

    public void addValidator(Company company) throws CompanyException {
        if (companyRepo.existsById(company.getId())) {
            throw new CompanyException(ErrorMsg.COMPANY_EXIST);
        }
        if (companyRepo.existsByName(company.getName())) {
            throw new CompanyException(ErrorMsg.COMPANY_NAME_EXISTS);
        }
        if (companyRepo.existsByNameOrEmail(company.getName(), company.getEmail())) {
            throw new CompanyException(ErrorMsg.COMPANY_EMAIL_EXISTS);
        }
    }

    public void updateValidator(Company company) throws CompanyException {
        if (!companyRepo.existsByNameAndId(company.getName(), company.getId()))
            throw new CompanyException(ErrorMsg.CAN_NOT_UPDATE_COMPANY);
    }

    public void isExistValidator(int companyId) throws CompanyException {
        if (!companyRepo.existsById(companyId)) {
            throw new CompanyException(ErrorMsg.NO_COMPANY);
        }
    }
}
