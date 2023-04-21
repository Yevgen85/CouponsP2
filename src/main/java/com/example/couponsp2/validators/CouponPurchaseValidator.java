package com.example.couponsp2.validators;

import com.example.couponsp2.beans.Coupon;
import com.example.couponsp2.beans.Customer;
import com.example.couponsp2.custom_exceptions.AuthorizationException;
import com.example.couponsp2.custom_exceptions.CouponException;
import com.example.couponsp2.custom_exceptions.ErrorMsg;
import com.example.couponsp2.repository.CustomersVsCouponsRepository;
import com.example.couponsp2.services.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CouponPurchaseValidator {

    private final CouponService couponService;
    private final CustomersVsCouponsRepository customersVsCouponsRepository;

    public void addValidator(Customer customer, Coupon coupon) throws CouponException, AuthorizationException {
        if (couponService.getById(coupon.getId()).getAmount() < 1)
            throw new CouponException(ErrorMsg.COUPON_AMOUNT_ZERO);

        if (customersVsCouponsRepository.existsByCouponIdAndCustomerId(coupon.getId(), customer.getId()))
            throw new CouponException(ErrorMsg.CANT_PURCHASE_SAME_COUPON_TWICE);

    }

}
