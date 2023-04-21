package com.example.couponsp2.beans;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;



@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "customers_vs_coupons")
public class CustomersVsCoupons {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne //(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne //(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;
}
