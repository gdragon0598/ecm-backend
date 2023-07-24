package com.huan.ecommerce.service;

import com.huan.ecommerce.dto.CategoryDTO;
import com.huan.ecommerce.entity.Category;

import java.util.Collection;

public interface ICategoryService {
    Category findCategoryById(Long id);

    Collection<CategoryDTO> findAll();
}
