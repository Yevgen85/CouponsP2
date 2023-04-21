package com.example.couponsp2.beans;

import com.example.couponsp2.services.CustomerVsCouponsService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "coupons")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @ManyToOne //(cascade = CascadeType.MERGE)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private String title;
    private String description;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    private int amount;
    private double price;
    private String image;


    @JsonIgnore
    @OneToMany(mappedBy = "coupon", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<CustomersVsCoupons> customersVsCoupons;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coupon coupon = (Coupon) o;
        return category.equals(coupon.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category);
    }
}
