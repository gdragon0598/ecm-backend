package com.huan.ecommerce.service.impl;

import com.huan.ecommerce.dto.CategoryDTO;
import com.huan.ecommerce.mapper.EntityDTOMapper;
import com.huan.ecommerce.repository.CategoryRepository;
import com.huan.ecommerce.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    /**
     * @return
     */
    @Autowired
    private EntityDTOMapper entityDTOMapper;

    @Override
    @Transactional(readOnly = true)
    public Collection<CategoryDTO> findAll() {
        Collection<CategoryDTO> categoryDTOS = categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "id"))
                                                                .parallelStream()
                                                                .map(entityDTOMapper::mapCategoryToDTO)
                                                                .toList();
        return categoryDTOS;
    }
}
