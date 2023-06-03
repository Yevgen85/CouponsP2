package com.example.couponsp2.validators;

import com.example.couponsp2.beans.Company;
import com.example.couponsp2.beans.Coupon;
import com.example.couponsp2.custom_exceptions.CompanyException;
import com.example.couponsp2.custom_exceptions.CouponException;
import com.example.couponsp2.custom_exceptions.ErrorMsg;
import com.example.couponsp2.repository.CouponRepository;
import com.example.couponsp2.repository.CustomersVsCouponsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CouponValidator {

    private final CouponRepository couponRepository;
    private final CustomersVsCouponsRepository customersVsCouponsRepository;

    public void addValidator(Coupon coupon) throws CouponException {
        if (couponRepository.existsById(coupon.getId())) {
            throw new CouponException(ErrorMsg.COUPON_ID_EXIST);
        }

        if (couponRepository.existsCouponByTitleAndCompanyId(coupon.getTitle(), coupon.getCompany().getId())) {
            throw new CouponException(ErrorMsg.COUPON_WITH_THIS_TITLE_ALREADY_EXISTS);
        }
    }

    public void updateValidator(Coupon coupon) throws CouponException {
        if (!couponRepository.existsCouponByIdAndCompanyId(coupon.getId(), coupon.getCompany().getId()))
            throw new CouponException(ErrorMsg.COUPON_NOT_EXIST);

        if (couponRepository.existsByTitleAndIdIsNot(coupon.getTitle(), coupon.getId())) {
            throw new CouponException(ErrorMsg.COUPON_WITH_THIS_TITLE_ALREADY_EXISTS);
        }
    }

    public void isExistValidator(int couponId) throws CouponException {
        if(!couponRepository.existsById(couponId))
            throw new CouponException(ErrorMsg.COUPON_NOT_EXIST);
    }
}
