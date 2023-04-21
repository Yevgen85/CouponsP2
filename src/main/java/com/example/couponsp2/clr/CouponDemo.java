//package com.example.couponsp2.clr;
//
//import com.example.couponsp2.beans.Company;
//import com.example.couponsp2.beans.Coupon;
//import com.example.couponsp2.custom_exceptions.CompanyException;
//import com.example.couponsp2.custom_exceptions.CouponException;
//import com.example.couponsp2.services.CategoryService;
//import com.example.couponsp2.services.CompanyService;
//import com.example.couponsp2.services.CouponService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDate;
//@Order(4)
//@Component
//@RequiredArgsConstructor
//public class CouponDemo implements CommandLineRunner {
//    public final CategoryService categoryService;
//    public final CouponService couponService;
//    public final CompanyService companyService;
//
//    @Override
//    public void run(String... args) throws Exception {
//        Coupon coupon = new Coupon();
//        coupon.setCategory(categoryService.getCategory(1));
//        coupon.setTitle("222couponTest");
//        coupon.setPrice((250.0));
//        coupon.setImage("111testCouponImage");
//        coupon.setAmount(5);
//        coupon.setDescription("111testCouponDescription");
//        coupon.setStartDate(LocalDate.of(2022, 12,1));
//        coupon.setEndDate(LocalDate.of(2023, 12, 30));
//        Company company = companyService.getOne(8);
//        coupon.setCompany(company);
//
//
//        try {
//            couponService.add(coupon);
//        }
//        catch (CouponException | CompanyException e) {
//            System.out.println(e.getMessage());
//        }
//
//        try {
//            Coupon coupon1 = couponService.getById(10);
//            coupon1.setAmount(555);
//            couponService.update(coupon1);
//        } catch (CouponException e) {
//            System.out.println(e.getMessage());
//        }
//
//
//        try {
//            couponService.delete(102);
//        }
//        catch (CouponException e) {
//            System.out.println(e.getMessage());
//        }
//
//        couponService.getAll().forEach(System.out::println);
//        Coupon coupon2 = couponService.getById(1);
//        System.out.println(coupon2);
//
//        couponService.getByCompanyId(1).forEach(System.out::println);
//        System.out.println(couponService.getByMaxPrice(5, 300).get(0));
//        couponService.getByMaxPrice(5, 100.0).forEach(System.out::println);
//        couponService.getByCategory(1, categoryService.getCategory(1)).forEach(System.out::println);
//
//    }
//}
