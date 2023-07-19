package com.huan.ecommerce.service.impl;

import com.huan.ecommerce.entity.Brand;
import com.huan.ecommerce.entity.Category;
import com.huan.ecommerce.repository.BrandRepository;
import com.huan.ecommerce.service.IBrandService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandService implements IBrandService {
    @Autowired
    private BrandRepository brandRepository;
    /**
     * @param id
     * @return
     */
    @Override
    public Brand findBrandById(Long id) {
        Brand brand = brandRepository.findById(id.longValue()).orElseThrow(() ->new EntityNotFoundException());
        return brand;
    }
}