package com.example.couponsp2.services;

import com.example.couponsp2.beans.Category;
import com.example.couponsp2.beans.Coupon;
import com.example.couponsp2.beans.Customer;

import com.example.couponsp2.beans.CustomersVsCoupons;
import com.example.couponsp2.custom_exceptions.AuthorizationException;
import com.example.couponsp2.custom_exceptions.CompanyException;
import com.example.couponsp2.custom_exceptions.CouponException;
import com.example.couponsp2.repository.CouponRepository;
import com.example.couponsp2.repository.CustomersVsCouponsRepository;
import com.example.couponsp2.validators.AuthorizationValidator;
import com.example.couponsp2.validators.CouponPurchaseValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerVsCouponsService {

    private final CustomersVsCouponsRepository customersVsCouponsRepository;
    private final CouponService couponService;
    private final CouponPurchaseValidator couponPurchaseValidator;
    private final AuthorizationValidator authorizationValidator;
    private final CouponRepository couponRepository;

    /**
     *This method checks if customer ID already purchased coupon ID
     */
    public boolean isExists(int couponId, int customerId) {
        return this.customersVsCouponsRepository.existsByCouponIdAndCustomerId(couponId, customerId);
    }

    /**
     *This method is adding a purchase of coupon by a customer
     *Checks if not expired
     *If exist - can't add more than one coupon
     *updates an amount of coupon
     *receives customer and coupon
     */
    public CustomersVsCoupons addCouponPurchase(Customer customer, Coupon coupon) throws CouponException, AuthorizationException, CompanyException {
        authorizationValidator.validateCustomer(customer);
        couponPurchaseValidator.addValidator(customer, coupon);


        Coupon coupon1 = couponRepository.findCouponById(coupon.getId());
        int amount = coupon1.getAmount();
        coupon1.setAmount(amount - 1);
        couponRepository.save(coupon1);

        CustomersVsCoupons customersVsCoupons = new CustomersVsCoupons();
        customersVsCoupons.setCoupon(couponService.getById(coupon.getId()));
        customersVsCoupons.setCustomer(customer);

        return this.customersVsCouponsRepository.save(customersVsCoupons);
    }

    /**
     * This method returns a list of all coupons of this customer
     */
    public List<Coupon> getCustomersCoupons(int customerId) throws AuthorizationException {
        authorizationValidator.validateCustomer();
        return getCouponsFromCVC(customersVsCouponsRepository.findAllByCustomer_Id(customerId));
    }

    /**
     * This method returns a list of all coupons of this customer
     * filtered by maximum price
     */
    public List<Coupon> getCustomersCoupons(int customerId, double maxPrice) throws AuthorizationException {
        authorizationValidator.validateCustomer();
        return getCustomersCoupons(customerId).stream().filter(coupon -> coupon.getPrice() <= maxPrice).collect(Collectors.toList());
    }

    /**
     * This method returns a list of all coupons of this customer
     * filtered by category
     */
    public List<Coupon> getCustomersCoupons(int customerId, Category category) throws AuthorizationException {
        authorizationValidator.validateCustomer();
        return getCouponsFromCVC(customersVsCouponsRepository.findAllByCustomer_IdAndCoupon_Category(customerId, category));
    }

    /**
     * This method takes out coupons from customers vs coupons table
     * and returns list of coupons
     */
    private List<Coupon> getCouponsFromCVC(List<CustomersVsCoupons> cvc) {
        return cvc.stream().map(CustomersVsCoupons::getCoupon).collect(Collectors.toList());
    }
}