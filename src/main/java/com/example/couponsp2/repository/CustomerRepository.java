package com.example.couponsp2.repository;

import com.example.couponsp2.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findCustomerByEmailAndPassword(String email, String password);
    boolean existsByEmailAndPassword(String email, String password);
    boolean existsByEmail(String email);
    boolean existsById(int customerId);

    boolean existsByEmailAndIdIsNot(String email, int id);
    Customer findCustomerByEmail(String email);

}
