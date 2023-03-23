package com.example.couponsp2.services;

import com.example.couponsp2.beans.Company;
import com.example.couponsp2.custom_exceptions.CompanyException;
import com.example.couponsp2.custom_exceptions.CustomerException;
import com.example.couponsp2.custom_exceptions.ErrorMsg;
import com.example.couponsp2.repository.CompanyRepository;
import com.example.couponsp2.validators.CompanyValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService{

    private final CompanyRepository companyRepository;
    private final CompanyValidator companyValidator;

    public boolean login(String email, String password) throws SQLException {
        return isExist(email, password);
    }

    /**
     *This method is showing company details
     * by company ID
     */
    public Company getDetails(int companyId) throws CompanyException {
        companyValidator.isExistValidator(companyId);
        return companyRepository.findById(companyId).get();
    }

    /**
     *This method is checking if the company is exists in the database
     * sending email and password
     * return true or false
     */
    public boolean isExist(String email, String  password) throws SQLException {
        return companyRepository.existsByEmailAndPassword(email, password);
    }

    /**
     *This method is checking if the company is exists in the database
     * sending company ID
     * return true or false
     */
    public boolean isExist(int companyId) throws SQLException {
        return companyRepository.existsById(companyId);
    }

    /**
     * This method adds a company to the database
     * checks if there aren't matches of name or email before
     * if there was a match, no company added
     */
    public Company addCompany(Company company) throws CompanyException {
        companyValidator.addValidator(company);
        return companyRepository.save(company);
    }

    /**
     * This method updates company
     * update can be done only if company name and company id are same as in the database
     */
    public Company updateCompany(Company company) throws CompanyException {
        companyValidator.updateValidator(company);
        return companyRepository.save(company);
    }

    /**
     * This method is deletes company from a database
     * checks if company exists and then deletes
     * if not exists, message of not exist is printed
     */
    public void deleteCompany(int companyId) throws CompanyException {
        companyValidator.isExistValidator(companyId);
        companyRepository.deleteById(companyId);
    }

    /**
     * This method returns a list of all companies are in a database
     */
    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    /**
     * This method returns a company by searching in the database by id
     * And returns it with list of coupons that are belongs to this company
     */
    public Company getOne(int companyId) throws CompanyException {
        companyValidator.isExistValidator(companyId);
        return companyRepository.findById(companyId).orElse(null);
    }


}
