package com.example.couponsp2.services;

import com.example.couponsp2.beans.*;
import com.example.couponsp2.custom_exceptions.AuthorizationException;
import com.example.couponsp2.custom_exceptions.CompanyException;
import com.example.couponsp2.custom_exceptions.CouponException;
import com.example.couponsp2.custom_exceptions.ErrorMsg;
import com.example.couponsp2.repository.CouponRepository;
import com.example.couponsp2.repository.CustomersVsCouponsRepository;
import com.example.couponsp2.validators.AuthorizationValidator;
import com.example.couponsp2.validators.CouponValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;
    private final CouponValidator couponValidator;
    private final CompanyService companyService;

    private final LoggedClientType loggedClientType;
    private final AuthorizationValidator authorizationValidator;

//    Company company = (Company) SecurityContextHolder.getContext().getAuthentication().getDetails();

//    private final UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
    /**
     *This method adds a coupon to the database
     * checks if there aren't matches of title with the database
     * if there was a match, no coupon added
     */
    public Coupon add(Coupon coupon) throws CouponException, AuthorizationException, CompanyException {
        authorizationValidator.validateCompany();
        System.out.println("Accepted Coupon:");
        System.out.println(coupon.toString());
        Company company = companyService.getOneForAddCoupon(loggedClientType.getId());
        coupon.setCompany(company);
        couponValidator.addValidator(coupon);
        return couponRepository.save(coupon);
    }

    /**
     *This method updates coupon
     *Update can be done if company id and coupon id has a match with the database
     */
    public Coupon update(Coupon coupon) throws CouponException, AuthorizationException, CompanyException {
        authorizationValidator.validateCompanyOrCustomer();
        Company company = couponRepository.findCouponById(coupon.getId()).getCompany();
        coupon.setCompany(company);
        couponValidator.updateValidator(coupon);
        return couponRepository.save(coupon);
    }

    /**
     *This method is checking if the coupon is exists in the database
     * sending coupon ID
     * return true or false
     */
    public boolean isExist(int id) {
        return couponRepository.existsById(id);
    }

    /**
     *This method is checking if the coupon is exists in the database
     * sending coupon ID and company ID
     * return true or false
     */
    public boolean isExist(int id, int companyId) {
        return couponRepository.existsCouponByIdAndCompanyId(id, companyId);
    }

    /**
     *This method is checking if the coupon is exists in the database
     * sending title and company ID
     * return true or false
     */
    public boolean isExist(String title, int companyId) {
        return this.couponRepository.existsCouponByTitleAndCompanyId(title, companyId);
    }

    /**
     *This method is deletes coupon from a database
     *checks if coupon exists and then deletes
     *if not exists, message of not exist is printed
     */
    public void delete(int couponId) throws CouponException, AuthorizationException {
        authorizationValidator.validateCompany();
        couponValidator.isExistValidator(couponId);
        couponRepository.deleteById(couponId);
    }
    /**
     *This method returns a list of all coupons in the database
     */
    public List<Coupon> getAll() throws AuthorizationException {
        authorizationValidator.validateCustomer();
        return couponRepository.findAll();
    }


    /**
     *This method returns a list of a company coupons by company id
     */
    public List<Coupon> getByCompanyId(int companyId) throws AuthorizationException {
        authorizationValidator.validateCompany();
        return couponRepository.findAllByCompanyId(companyId);
    }

    /**
     *This method returns a list of a company coupons
     *Filtered by category
     */
    public List<Coupon> getByCategory(int companyId, Category category) throws AuthorizationException {
        authorizationValidator.validateCompany();
        return couponRepository.findByCompanyIdAndCategory(companyId, category);
    }

    public List<Coupon> getByCategory(Category category) throws AuthorizationException {
        authorizationValidator.validateCustomer();
        return couponRepository.findByCategory(category);
    }

    /**
     *This method returns a list of a company coupons
     *Filtered by maximum price
     */
    public List<Coupon> getByMaxPrice(int companyId, double maxPrice) throws AuthorizationException {
        authorizationValidator.validateCompany();
        return couponRepository.findByCompanyIdAndPriceIsLessThanEqual(companyId, maxPrice);
    }

    /**
     *This method returns a company by searching in the database by id
     * And returns it with list of coupons that are belongs to this company
     */
    public Coupon getById(int couponId) throws CouponException, AuthorizationException {
        authorizationValidator.validateCompanyOrCustomer();
        couponValidator.isExistValidator(couponId);
        return couponRepository.findCouponById(couponId);
    }

    /**
     *This method returns all the expired coupons
     */

    public List<Coupon> getExpiredCoupons() throws InterruptedException {
        System.out.println("scheduled");
        return couponRepository.findAllByEndDateBefore(LocalDate.now());
    }

    /**
     *This method deletes all the expired coupons
     */
//    @Scheduled(cron = "0 0 0 * * *") ==> @Scheduled(cron = "@midnight")
    @Scheduled(cron = "@midnight")
    public void cleanExpiredDateCoupons() throws InterruptedException {
        System.out.println("Cleaning Expired Coupons...");
        couponRepository.deleteAllByEndDateBefore(LocalDate.now());
    }



}
