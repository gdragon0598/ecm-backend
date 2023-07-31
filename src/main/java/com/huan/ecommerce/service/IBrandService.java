package com.huan.ecommerce.service;

import com.huan.ecommerce.dto.BrandDTO;
import org.springframework.data.domain.Pageable;

import java.util.Collection;


public interface IBrandService {
    Collection<BrandDTO> findAll(Pageable pageable);
}
