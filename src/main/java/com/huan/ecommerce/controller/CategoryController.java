package com.huan.ecommerce.controller;

import com.huan.ecommerce.dto.CategoryDTO;
import com.huan.ecommerce.service.ICategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping("")
    public Collection<CategoryDTO> getAllCategories() {
        Collection<CategoryDTO> categoryDTOS = categoryService.findAll();
        return categoryDTOS;
    }

}
