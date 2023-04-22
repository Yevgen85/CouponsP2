package com.example.couponsp2.services;

import com.example.couponsp2.beans.ClientType;
import com.example.couponsp2.beans.Company;
import com.example.couponsp2.beans.LoggedClientType;
import com.example.couponsp2.custom_exceptions.AuthorizationException;
import com.example.couponsp2.custom_exceptions.CompanyException;
import com.example.couponsp2.custom_exceptions.ErrorMsg;
import com.example.couponsp2.dto.CompanyDTO;
import com.example.couponsp2.repository.CompanyRepository;
import com.example.couponsp2.validators.AuthorizationValidator;
import com.example.couponsp2.validators.CompanyValidator;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyValidator companyValidator;
    public final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final LoggedClientType loggedClientType;

    private final AuthorizationValidator authorizationValidator;

//    private final UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
    public boolean login(String email, String password) throws SQLException {
        return isExist(email, password);
    }

    /**
     *This method is showing company details
     * by company ID
     */
    public CompanyDTO getDetails(int companyId) throws CompanyException, AuthorizationException {
        authorizationValidator.validateAdminOrCompany();
        companyValidator.isExistValidator(companyId);
        return convertToCompanyDTO(companyRepository.findById(companyId).get()) ;
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
    public CompanyDTO addCompany(Company company) throws CompanyException, AuthorizationException {
        authorizationValidator.validateAdmin();
        companyValidator.addValidator(company);
        company.setPassword(passwordEncoder.encode(company.getPassword()));
        return convertToCompanyDTO(companyRepository.save(company));
    }

    /**
     * This method updates company
     * update can be done only if company name and company id are same as in the database
     */
    public CompanyDTO updateCompany(int id, Company company) throws CompanyException, AuthorizationException {
        authorizationValidator.validateAdmin();
        companyValidator.updateValidator(company);
        Company companyToUpdate = companyRepository.findByIdAndName(company.getId(), company.getName());
        if (companyToUpdate.getId() != id) {
            throw new CompanyException(ErrorMsg.LOGGED_ID_ERROR);
        }
        company.setPassword(companyToUpdate.getPassword());
        return convertToCompanyDTO(companyRepository.save(company));
    }

    /**
     * This method is deletes company from a database
     * checks if company exists and then deletes
     * if not exists, message of not exist is printed
     */
    public void deleteCompany(int companyId) throws CompanyException, AuthorizationException {
        authorizationValidator.validateAdmin();
        companyValidator.isExistValidator(companyId);
        companyRepository.deleteById(companyId);
    }

    /**
     * This method returns a list of all companies are in a database
     */
    public List<CompanyDTO> getAll() throws AuthorizationException {
        authorizationValidator.validateAdmin();
        return convertToListOfCompanyDTO(companyRepository.findAll());

    }

    /**
     * This method returns a company by searching in the database by id
     * And returns it with list of coupons that are belongs to this company
     */
    public CompanyDTO getOne(int companyId) throws CompanyException {
        companyValidator.isExistValidator(companyId);
        return convertToCompanyDTO(companyRepository.findById(companyId).get());
    }

    public Company getOneForAddCoupon(int companyId) throws CompanyException {
        companyValidator.isExistValidator(companyId);
        return companyRepository.findById(companyId).get();
    }

    public UserDetails findByEmail(String username) {
        return companyRepository.findByEmail(username);
    }

    private CompanyDTO convertToCompanyDTO(Company company) {
        return this.modelMapper.map(company, CompanyDTO.class);
    }

    private List<CompanyDTO> convertToListOfCompanyDTO(List<Company> companies) {
        return companies.stream().map(this::convertToCompanyDTO).collect(Collectors.toList());
    }


    public Map<String, Object> buildClaims(Company company) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", company.getUsername());
        claims.put("name", company.getName());
        return claims;
    }

}
