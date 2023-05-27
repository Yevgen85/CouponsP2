package com.example.couponsp2.controller;

import com.example.couponsp2.beans.ClientType;
import com.example.couponsp2.beans.Company;
import com.example.couponsp2.beans.LoggedClientType;
import com.example.couponsp2.custom_exceptions.AuthorizationException;
import com.example.couponsp2.custom_exceptions.CompanyException;
import com.example.couponsp2.custom_exceptions.CustomerException;
import com.example.couponsp2.dto.CompanyDTO;
import com.example.couponsp2.dto.PasswordChangeDto;
import com.example.couponsp2.services.CompanyService;
import com.example.couponsp2.validators.AuthorizationValidator;
import jakarta.validation.Valid;
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
    public CompanyDTO addCompany(@Valid @RequestBody Company company) throws CompanyException, AuthorizationException {
        return  companyService.addCompany(company);
    }

    @PutMapping("/{id}")
    public CompanyDTO updateCompany(@PathVariable int id, @Valid @RequestBody  Company company) throws CompanyException, AuthorizationException {
        System.out.println("received param id: " + id);
        System.out.println("received param company: " + company);
        return companyService.updateCompany(id, company);
    }

    @PutMapping("/update-password/{id}")
    public void updateCompanyPassword(@PathVariable int id, @Valid @RequestBody PasswordChangeDto passwordChangeDto) throws CompanyException {
        System.out.println(id + " + " + passwordChangeDto);
        companyService.changePassword(id, passwordChangeDto);
    }



}

