package com.huan.ecommerce.service.impl;

import com.huan.ecommerce.dto.CategoryDTO;
import com.huan.ecommerce.entity.Category;
import com.huan.ecommerce.mapper.EntityDTOMapper;
import com.huan.ecommerce.repository.CategoryRepository;
import com.huan.ecommerce.service.ICategoryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

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
        Category category = categoryRepository.findById(id).orElseThrow(() ->new EntityNotFoundException("Category not found" + id));
        return category;
    }

    /**
     * @return
     */
    @Override
    public Collection<CategoryDTO> findAll() {
        Collection<CategoryDTO> categoryDTOS = categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "id"))
                                                                .parallelStream()
                                                                .map(EntityDTOMapper::mapCategoryToDTO)
                                                                .toList();
        return categoryDTOS;
    }
}
