package com.example.couponsp2.repository;

import com.example.couponsp2.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

//    Company findByEmailAndPassword(String email, String password);
    Company findCompanyByEmailAndPassword(String email, String password);
    boolean existsByEmailAndPassword(String email, String password);

    boolean existsByNameOrEmail(String name, String email);

    boolean existsByNameAndId(String name, int id);


    boolean existsById(int companyId);


    boolean existsByName(String name);

    UserDetails findByEmail(String email);

    Company findByIdAndName(int id, String name);
    Company findByEmailAndId(String email, int id);
}
