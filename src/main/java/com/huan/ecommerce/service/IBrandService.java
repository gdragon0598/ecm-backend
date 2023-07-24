package com.huan.ecommerce.service;

import com.huan.ecommerce.dto.BrandDTO;
import com.huan.ecommerce.dto.CategoryDTO;
import com.huan.ecommerce.entity.Brand;
import org.springframework.data.domain.Pageable;

import java.util.Collection;


public interface IBrandService {
    Brand findBrandById(Long id);

    Collection<BrandDTO> findAll(Pageable pageable);
}
