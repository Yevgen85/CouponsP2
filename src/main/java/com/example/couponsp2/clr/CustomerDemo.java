//package com.example.couponsp2.clr;
//
//import com.example.couponsp2.beans.Category;
//import com.example.couponsp2.beans.Coupon;
//import com.example.couponsp2.beans.Customer;
//import com.example.couponsp2.services.*;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//@Order(5)
//@Component
//@RequiredArgsConstructor
//public class CustomerDemo implements CommandLineRunner {
//
//
//    private final  CustomerService customerService;
//
//    private final CustomerVsCouponsService cvcService;
//
//    private final CouponService couponService;
//
//    private final CategoryService categoryService;
//
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        Customer customer = customerService.getOne(10);
//        Coupon coupon = null;
//        try {
//            coupon = couponService.getById(22);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//        try {
//            assert coupon != null;
//            cvcService.addCouponPurchase(customer, coupon);
//        } catch (Exception e) {
//            System.out.println(e.getMessage() + "////////////////////////////////////////////////////////////////////////////////");
//        }
//
//        cvcService.getCustomersCoupons(10).forEach(System.out::println);
//
//        cvcService.getCustomersCoupons(10, 173).forEach(System.out::println);
//
//        Category category = categoryService.getCategory(2);
//        cvcService.getCustomerCoupons(1, category).forEach(System.out::println);
//
//    }
//}
