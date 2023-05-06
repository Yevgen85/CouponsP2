package com.example.couponsp2.controller;

import com.example.couponsp2.beans.Category;
import com.example.couponsp2.custom_exceptions.AuthorizationException;
import com.example.couponsp2.dto.CompanyDTO;
import com.example.couponsp2.repository.CategoryRepository;
import com.example.couponsp2.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping()
    public List<Category> getCategories() {
        return categoryService.getAllCategories();
    }
}
