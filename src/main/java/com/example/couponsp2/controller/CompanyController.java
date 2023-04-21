package com.example.couponsp2.controller;

import com.example.couponsp2.beans.ClientType;
import com.example.couponsp2.beans.Company;
import com.example.couponsp2.beans.LoggedClientType;
import com.example.couponsp2.custom_exceptions.AuthorizationException;
import com.example.couponsp2.custom_exceptions.CompanyException;
import com.example.couponsp2.dto.CompanyDTO;
import com.example.couponsp2.services.CompanyService;
import com.example.couponsp2.validators.AuthorizationValidator;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/company")
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping()
    public List<CompanyDTO> getCompanies() throws AuthorizationException {
        return this.companyService.getAll();
    }

    @GetMapping("/{id}")
    public CompanyDTO getCompanyDetails(@PathVariable int id) throws AuthorizationException, CompanyException {
        return companyService.getDetails(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCompany (@PathVariable int id) throws CompanyException, AuthorizationException {
        companyService.deleteCompany(id);
    }

    @PostMapping()
    public CompanyDTO addCompany(@RequestBody Company company) throws CompanyException, AuthorizationException {
        return  companyService.addCompany(company);
    }

    @PutMapping("/{id}")
    public Company updateCompany(@PathVariable int id, @RequestBody  Company company) throws CompanyException, AuthorizationException {
        return companyService.updateCompany(id, company);
    }

//    @PostMapping("/filter")
//    public boolean isExist(@RequestParam String email, @RequestParam String password) throws SQLException {
//        return companyService.isExist(email, password);
//    }



}

