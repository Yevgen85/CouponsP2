package com.example.couponsp2.services;

import com.example.couponsp2.beans.Category;
import com.example.couponsp2.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService{


    private final CategoryRepository categoryRepository;


    public void addCategory(Category category) {
        if (!isExists(category.getName())) {
            this.categoryRepository.save(category);
        }
        System.out.println("Category exists");
    }

    /**
     * This method returns a category by ID
     */
    public Category getCategory(int id) {
        return this.categoryRepository.getCategoryById(id);
    }

    /**
     * This method deletes category
     */
    public void deleteCategory(Category category) {
        this.categoryRepository.delete(category);
    }

    /**
     * This method checks if category exist by name
     * return true or false
     */
    public boolean isExists(String name) {
        return this.categoryRepository.existsCategoryByName(name);
    }
}
