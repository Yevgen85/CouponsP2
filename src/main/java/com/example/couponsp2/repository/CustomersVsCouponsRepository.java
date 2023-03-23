package com.example.couponsp2.repository;

import com.example.couponsp2.beans.Category;

import com.example.couponsp2.beans.CustomersVsCoupons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomersVsCouponsRepository extends JpaRepository<CustomersVsCoupons, Integer> {

    CustomersVsCoupons findByCustomer_IdAndCoupon_Id(int customerId, int couponId);

    List<CustomersVsCoupons> findAllByCustomer_Id(int customerId);

    List<CustomersVsCoupons> findAllByCustomer_IdAndCoupon_Category(int customerId, Category category);

    boolean existsByCouponIdAndCustomerId(int couponId, int customerId);

}
