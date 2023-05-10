package com.example.couponsp2.clr;

import com.example.couponsp2.beans.*;
import com.example.couponsp2.custom_exceptions.CompanyException;
import com.example.couponsp2.custom_exceptions.CouponException;
import com.example.couponsp2.custom_exceptions.CustomerException;
import com.example.couponsp2.services.*;
import com.example.couponsp2.validators.AuthorizationValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Random;
@Order(1)
@Component
@RequiredArgsConstructor
public class AutomationDemo implements CommandLineRunner {

    public final CategoryService categoryService;
    public final CustomerService customerService;
    public final CompanyService companyService;
    public final CouponService couponService;
    public final CustomerVsCouponsService customerVsCouponsService;
    public final PasswordEncoder passwordEncoder;
    public final LoggedClientType loggedClientType;
    @Override
    public void run(String... args) throws Exception {
        initEntities();
    }

    public void initEntities() throws CompanyException, CustomerException, CouponException, SQLException {

        Random random = new Random();

        for (int i = 1; i <= 10; i++) {
            Company company = new Company();
            company.setName(" Company Name" + i);
            company.setEmail(i + "@company.com");
            company.setPassword("password" + i);

            try {
                loggedClientType.setClientType(ClientType.ADMINISTRATOR);
                companyService.addCompany(company);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        Category category1 = new Category();
        category1.setName("Food");

        Category category2 = new Category();
        category2.setName("Hotels");

        Category category3 = new Category();
        category3.setName("Clothes");

        Category category4 = new Category();
        category4.setName("Spa");

        Category category5 = new Category();
        category5.setName("Flights");

        categoryService.addCategory(category1);
        categoryService.addCategory(category2);
        categoryService.addCategory(category3);
        categoryService.addCategory(category4);
        categoryService.addCategory(category5);

        for (int i = 1; i <= 10; i++) {
            Customer customer = new Customer();
            customer.setEmail(i + "customer@mail.com");
            customer.setPassword(i + "customerPassword");
            customer.setFirstName(i + "Customer First Name");
            customer.setLastName(i + "Customer Last Name");

            try {
                loggedClientType.setClientType(ClientType.ADMINISTRATOR);
                customerService.addCustomer(customer);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        loggedClientType.setClientType(ClientType.COMPANY);
        for (int i = 1; i <= 100; i++) {
            Coupon coupon = new Coupon();
            coupon.setCategory(categoryService.getCategory(1 + random.nextInt(5)));
            coupon.setTitle(i + "couponTitle");
            coupon.setPrice(50.0 + random.nextInt(250));
            coupon.setImage(i + "couponImage");
            coupon.setAmount(1 + random.nextInt( 10));
            coupon.setDescription(i + "couponDescription");
            coupon.setStartDate(LocalDate.of(2023, 12,1));
            coupon.setEndDate(LocalDate.of(2024, 12, 1 + random.nextInt(30)));
            loggedClientType.setId(1 + random.nextInt(10));
//            coupon.setCompany(companyService.getOneForAddCoupon(1 + random.nextInt(10)));


            try {

                couponService.add(coupon);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
        loggedClientType.setClientType(ClientType.CUSTOMER);
        for (int i = 1; i <= 100; i++) {

            try {
                Customer customer = customerService.getOneForCouponPurchase(1 + random.nextInt(10));
                Coupon coupon = couponService.getById(i);
                customerVsCouponsService.addCouponPurchase(customer, coupon);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
