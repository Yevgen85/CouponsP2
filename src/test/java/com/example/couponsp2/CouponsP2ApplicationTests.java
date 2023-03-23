package com.example.couponsp2;

import com.example.couponsp2.beans.Category;
import com.example.couponsp2.beans.Company;
import com.example.couponsp2.beans.Coupon;
import com.example.couponsp2.beans.Customer;
import com.example.couponsp2.custom_exceptions.CompanyException;
import com.example.couponsp2.custom_exceptions.CouponException;
import com.example.couponsp2.custom_exceptions.CustomerException;
import com.example.couponsp2.services.CategoryService;
import com.example.couponsp2.services.CompanyService;
import com.example.couponsp2.services.CouponService;
import com.example.couponsp2.services.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Random;

@SpringBootTest
class CouponsP2ApplicationTests {

    @Test
    void contextLoads() {
    }



}
