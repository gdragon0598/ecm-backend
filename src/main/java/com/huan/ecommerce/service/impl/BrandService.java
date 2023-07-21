package com.huan.ecommerce.service.impl;

import com.huan.ecommerce.dto.BrandDTO;
import com.huan.ecommerce.dto.CategoryDTO;
import com.huan.ecommerce.entity.Brand;
import com.huan.ecommerce.entity.Category;
import com.huan.ecommerce.mapper.EntityDTOMapper;
import com.huan.ecommerce.repository.BrandRepository;
import com.huan.ecommerce.service.IBrandService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;

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
        Brand brand = brandRepository.findById(id.longValue()).orElseThrow(() ->new EntityNotFoundException("Brand not found: Brand id " + id));
        return brand;
    }

    /**
     * @return
     */
    @Override
    public Collection<BrandDTO> findAll(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "id"));
        Collection<BrandDTO> brandDTOS = brandRepository.findAll(pageable).map(EntityDTOMapper::mapBrandToDTO).toList();
        return brandDTOS;
    }


}