package com.example.couponsp2.controller;

import com.example.couponsp2.beans.Category;
import com.example.couponsp2.beans.Coupon;
import com.example.couponsp2.beans.Customer;
import com.example.couponsp2.custom_exceptions.AuthorizationException;
import com.example.couponsp2.custom_exceptions.CompanyException;
import com.example.couponsp2.custom_exceptions.CouponException;
import com.example.couponsp2.custom_exceptions.CustomerException;
import com.example.couponsp2.dto.CustomerDTO;
import com.example.couponsp2.services.CouponService;
import com.example.couponsp2.services.CustomerVsCouponsService;
import com.example.couponsp2.validators.AuthorizationValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/coupon")
@CrossOrigin(origins = "*")
public class CouponController {

private final CouponService couponService;


    @GetMapping()
    public List<Coupon> getAllCoupons() throws AuthorizationException {
        return this.couponService.getAll();
    }

    @GetMapping("/company/{id}")
    public List<Coupon> getCompanyCoupons(@PathVariable int id) throws AuthorizationException, CompanyException {
        return this.couponService.getByCompanyId(id);
    }

    @GetMapping("/{id}")
    public Coupon getSingleCoupon(@PathVariable int id) throws AuthorizationException, CouponException {
        return couponService.getById(id);
    }


    @GetMapping("/filter/category/{companyId}")
    public List<Coupon> getByCategory(@PathVariable int companyId, @RequestParam Category category) throws AuthorizationException {
        return couponService.getByCategory(companyId, category);
    }

    //for client
    @GetMapping("/filter/category/")
    public List<Coupon> getByCategory(@RequestParam Category category) throws AuthorizationException {
        return couponService.getByCategory(category);
    }

    @GetMapping("/filter/maxPrice/{companyId}")
    public List<Coupon> getByMaxPrice(@PathVariable int companyId, @RequestParam double maxPrice) throws AuthorizationException {
        return couponService.getByMaxPrice(companyId, maxPrice);
    }

    @DeleteMapping("/{id}")
    public void deleteCoupon (@PathVariable int id) throws CouponException, AuthorizationException {
        couponService.delete(id);
    }

    @PostMapping()
    public Coupon addCoupon(@RequestBody Coupon coupon) throws CouponException, AuthorizationException, CompanyException {
        System.out.println("Adding coupon: " + coupon.toString());
        return couponService.add(coupon);
    }

    @PutMapping("/{id}")
    public Coupon updateCoupon(@RequestBody  Coupon coupon) throws CouponException, AuthorizationException, CompanyException {
        System.out.println(coupon.toString());
        return couponService.update(coupon);
    }

}
