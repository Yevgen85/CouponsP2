package com.example.couponsp2.clr;

import com.example.couponsp2.beans.Company;
import com.example.couponsp2.beans.Coupon;
import com.example.couponsp2.beans.Customer;
import com.example.couponsp2.custom_exceptions.CompanyException;
import com.example.couponsp2.custom_exceptions.CouponException;
import com.example.couponsp2.custom_exceptions.CustomerException;
import com.example.couponsp2.repository.CouponRepository;
import com.example.couponsp2.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Random;

//@Order(1)

@Component
@RequiredArgsConstructor
public class AdminDemo implements CommandLineRunner {

    public final CustomerService customerService;
    public final CompanyService companyService;
    public final CouponService couponService;
    public final CouponRepository couponRepository;

    @Override
    public void run(String... args) throws Exception {

        Company company = companyService.getOne(1);
        company.setPassword("321321");
        company.setName("qwer");
        try {
            companyService.updateCompany(company);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        companyService.getAll().forEach(System.out::println);

        try {
            System.out.println(companyService.getOne(111));
        } catch (CompanyException e) {
            System.out.println(e.getMessage());
        }


        try {
            System.out.println(customerService.getOne(111));
        } catch (CustomerException e) {
            System.out.println(e.getMessage());
        }
        Customer customer = customerService.getOne(1);
        System.out.println(customer);
        customer.setEmail("Updated111Email");
        customerService.updateCustomer(customer);
        customerService.getAll().forEach(System.out::println);
        try {
            companyService.deleteCompany(2);
        } catch (CompanyException e) {
            System.out.println(e.getMessage());
        }
        try {
            customerService.deleteCustomer(2);
        } catch (CustomerException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(companyService.getDetails(3));

//        couponService.cleanExpiredDateCoupons();
        try {
            Coupon coupon = couponService.getById(1);
            coupon.setEndDate(LocalDate.of(2022,12,1));
            couponService.update(coupon);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            Coupon coupon = couponService.getById(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
//        Coupon coupon2 = couponService.getById(4).get(0);
//        coupon2.setEndDate(LocalDate.of(2022,12,1));
//        couponService.update(coupon2);
//        Coupon coupon3 = couponService.getById(5).get(0);
//        coupon3.setEndDate(LocalDate.of(2022,12,1));
//        couponService.update(coupon3);

//        couponService.cleanExpiredDateCoupons();
    }

}
