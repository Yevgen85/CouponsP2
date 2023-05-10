package com.example.couponsp2.controller;
import com.example.couponsp2.beans.*;
import com.example.couponsp2.custom_exceptions.AuthorizationException;
import com.example.couponsp2.custom_exceptions.CompanyException;
import com.example.couponsp2.custom_exceptions.CouponException;
import com.example.couponsp2.custom_exceptions.CustomerException;
import com.example.couponsp2.dto.CompanyDTO;
import com.example.couponsp2.services.CompanyService;
import com.example.couponsp2.services.CouponService;
import com.example.couponsp2.services.CustomerService;
import com.example.couponsp2.services.CustomerVsCouponsService;
import com.example.couponsp2.validators.AuthorizationValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/customer-vs-coupons")
@CrossOrigin(origins = "*")
public class CustomerVsCouponsController {

    private final CustomerVsCouponsService customerVsCouponsService;
    private final CustomerService customerService;
    private final CompanyService companyService;

    private final CouponService couponService;
    private final LoggedClientType loggedClientType;

    @GetMapping("/{id}")
    public List<Coupon> getCustomersCoupons(@PathVariable int id) throws AuthorizationException {
        return customerVsCouponsService.getCustomersCoupons(id);
    }

    @GetMapping("/{id}/{maxPrice}")
    public List<Coupon> getCustomersCoupons(@PathVariable int id,@PathVariable double maxPrice) throws AuthorizationException {
        return customerVsCouponsService.getCustomersCoupons(id, maxPrice);
    }

    @GetMapping("/{id}/{category}")
    public List<Coupon> getCustomersCoupons(@PathVariable int id, @PathVariable Category category) throws AuthorizationException {
        return customerVsCouponsService.getCustomersCoupons(id, category);
    }

    @PostMapping("/{id}")
    public Coupon addCouponPurchase(@PathVariable int id, @RequestBody Coupon coupon) throws AuthorizationException, CouponException, CompanyException, CustomerException {

        // Move this 4 rows to service!!!
        Customer customer = customerService.getOneForCouponPurchase(loggedClientType.getId());
        Coupon couponToAdd = couponService.getById(coupon.getId());
        System.out.println(couponToAdd.toString());
        System.out.println(customer.toString());

        customerVsCouponsService.addCouponPurchase(customer, couponToAdd);

        return coupon;
    }
}
