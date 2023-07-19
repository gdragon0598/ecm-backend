package com.huan.ecommerce.service.impl;

import com.huan.ecommerce.entity.Category;
import com.huan.ecommerce.repository.CategoryRepository;
import com.huan.ecommerce.service.ICategoryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    /**
     * @param id
     * @return
     */
    @Override
    public Category findCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() ->new EntityNotFoundException());
        return category;
    }
}
