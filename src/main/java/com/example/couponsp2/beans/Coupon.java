package com.example.couponsp2.beans;

import com.example.couponsp2.services.CustomerVsCouponsService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotEmpty(message = "Title is required, can not be empty!")
    @Size(min = 2, max = 100, message = "Title must be between 2 to 100 symbols length")
    private String title;

    @NotEmpty(message = "Description is required, can not be empty!")
    @Size(min = 2, max = 100, message = "Description must be between 2 to 100 symbols length")
    private String description;

    @FutureOrPresent(message = "Start date must be today or after today!")
    @Column(name = "start_date")
    private LocalDate startDate;

    @Future(message = "End date must be from tomorrow!")
    @Column(name = "end_date")
    private LocalDate endDate;

    @NotNull(message = "Amount is required, can not be empty!")
    @Min(value = 0, message = "Amount must be minimum 0!")
    @Max(value = 1000, message = "Amount can be maximum 1,000")
    private int amount;

    @NotNull(message = "Price is required, can not be empty!")
    @Min(value = 1, message = "Price must be minimum 1!")
    @Max(value = 1000000, message = "Price can be maximum 1,000,000")
    private double price;

    @NotEmpty(message = "Image is required, can not be empty!")
    @Size(min = 1, max = 100, message = "Image must be between 1 to 100 symbols length")
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
