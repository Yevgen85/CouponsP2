package com.example.couponsp2.clr;

import com.example.couponsp2.beans.Company;
import com.example.couponsp2.custom_exceptions.CompanyException;
import com.example.couponsp2.services.CategoryService;
import com.example.couponsp2.services.CompanyService;
import com.example.couponsp2.services.CouponService;
import com.example.couponsp2.services.CustomerVsCouponsService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompanyDemo implements CommandLineRunner {

    public final CompanyService companyService;
    public final CategoryService categoryService;
    public final CouponService couponService;
    public final CustomerVsCouponsService cvcService;

    @Override
    public void run(String... args) throws Exception {

        System.out.println(companyService.isExist(1));
        Company company = new Company();
        company.setName("DemoTestName");
        company.setEmail("demoEmail");
        company.setPassword("12345Test");
        try {
            companyService.addCompany(company);
        } catch (CompanyException e) {
            System.out.println(e.getMessage());
        }


        Company company1 = companyService.getOne(11);
        company1.setEmail("UpdatedTestEmail");

        try {
            companyService.updateCompany(company1);
        } catch (CompanyException e) {
            System.out.println(e.getMessage());
        }


        try {
            companyService.deleteCompany(12);
        } catch (CompanyException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(company1);
        companyService.getAll().forEach(System.out::println);
        System.out.println(companyService.login(company1.getEmail(), company1.getPassword()));

//        cvcService.getCustomersCoupons(1).forEach(System.out::println);

    }
}
