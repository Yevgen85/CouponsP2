package com.example.couponsp2.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE)
    private List<Coupon> coupons = new ArrayList<>();


}
