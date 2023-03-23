package com.example.couponsp2.repository;

import com.example.couponsp2.beans.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    boolean existsCategoryByName(String name);
    Category getCategoryById(int id);
}
