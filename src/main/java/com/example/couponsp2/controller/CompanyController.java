package com.example.couponsp2.controller;

import com.example.couponsp2.beans.Company;
import com.example.couponsp2.custom_exceptions.CompanyException;
import com.example.couponsp2.services.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/company")
public class CompanyController {



    private final CompanyService companyService;

    @GetMapping()
    public List<Company> getCompanies() {
        return this.companyService.getAll();
    }

    @GetMapping("/{id}")
    public Company getCompanyDetails(@PathVariable int id) throws CompanyException {
            return companyService.getDetails(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCompany (@PathVariable int id) throws CompanyException {
        companyService.deleteCompany(id);
    }

    @PostMapping()
    public Company addCompany(@RequestBody Company company) throws CompanyException {
        return companyService.addCompany(company);
    }

    @PutMapping("/{id}")
    public Company updateCompany(@PathVariable int id, @RequestBody  Company company) throws CompanyException {
        return companyService.updateCompany(company);
    }

    @PostMapping("/filter")
    public boolean isExist(@RequestParam String email, @RequestParam String password) throws SQLException {
        return companyService.isExist(email, password);
    }
}

