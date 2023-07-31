package com.huan.ecommerce.service;

import com.huan.ecommerce.dto.CategoryDTO;

import java.util.Collection;

public interface ICategoryService {
    Collection<CategoryDTO> findAll();
}
