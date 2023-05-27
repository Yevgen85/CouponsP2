package com.example.couponsp2.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ToString
@NoArgsConstructor
@Setter()
@Getter

@Entity
@Table(name = "customers")
public class Customer implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int id;

    @NotEmpty(message = "First Name is required, can not be empty!")
    @Size(min = 2, max = 100, message = "First Name must be between 2 to 100 symbols length")
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty(message = "Last Name is required, can not be empty!")
    @Size(min = 2, max = 100, message = "Last Name must be between 2 to 100 symbols length")
    @Column(name = "last_name")
    private String lastName;

    @NotEmpty(message = "Email is required, can not be empty!")
//    @Email(regexp = "^\\w+([\\.-]?\\w+)*@([\\w-]+\\.)+[\\w]{2,4}$", message = "Invalid email format")
    private String email;

    @Size(min = 6, max = 100, message = "Password must be between 6 to 100 symbols length")
    private String password;

    //    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JsonIgnore
    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<CustomersVsCoupons> customersVsCouponsList;

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
