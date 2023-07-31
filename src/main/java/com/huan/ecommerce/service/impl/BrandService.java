package com.huan.ecommerce.service.impl;

import com.huan.ecommerce.dto.BrandDTO;
import com.huan.ecommerce.mapper.EntityDTOMapper;
import com.huan.ecommerce.repository.BrandRepository;
import com.huan.ecommerce.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class BrandService implements IBrandService {
    @Autowired
    private BrandRepository brandRepository;

    /**
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<BrandDTO> findAll(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "id"));
        Collection<BrandDTO> brandDTOS = brandRepository.findAll(pageable).map(EntityDTOMapper::mapBrandToDTO).toList();
        return brandDTOS;
    }


}