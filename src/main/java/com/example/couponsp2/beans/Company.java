package com.example.couponsp2.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "companies")
public class Company implements UserDetails {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name is required, can not be empty!")
    @Size(min = 2, max = 100, message = "Name must be between 2 to 100 symbols length")
    @Column(name = "name")
    private String name;
    @NotEmpty(message = "Email is required, can not be empty!")
//    @Email(regexp = "^\\w+([\\.-]?\\w+)*@([\\w-]+\\.)+[\\w]{2,4}$", message = "Invalid email format")
    private String email;
    @Size(min = 6, max = 100, message = "Password must be between 6 to 100 symbols length")
    private String password;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE)
    private List<Coupon> coupons = new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
