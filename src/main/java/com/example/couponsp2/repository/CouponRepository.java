package com.example.couponsp2.repository;

import com.example.couponsp2.beans.Category;
import com.example.couponsp2.beans.Company;
import com.example.couponsp2.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {


    boolean existsCouponByTitleAndCompanyId(String title, int companyId);
    boolean existsByTitle(String title);
    boolean existsByCompanyId(int id);

    boolean existsCouponByIdAndCompanyId(int id, int companyId);
    Coupon findByTitleAndCompanyId(String title, int companyId);
    Coupon findByCompanyIdAndId(int companyId, int couponId);
    List<Coupon> findAllByCompanyId(int companyId);
    List<Coupon> findAllByCompanyIdAndId(int companyId, int id);
    List<Coupon> findByCompanyIdAndCategory(int companyId, Category category);
    List<Coupon> findByCategory(Category category);
    List<Coupon> findByCompanyIdAndPriceIsLessThanEqual(int companyId, double price);
    List<Coupon> findAllById(int id);

    Coupon findCouponById(int id);
    List<Coupon> findAllByEndDateBefore(LocalDate localDate);

    void deleteAllByEndDateBefore(LocalDate localDate);


}
