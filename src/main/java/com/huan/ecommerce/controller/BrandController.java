package com.huan.ecommerce.controller;

import com.huan.ecommerce.dto.BrandDTO;
import com.huan.ecommerce.dto.CategoryDTO;
import com.huan.ecommerce.service.IBrandService;
import com.huan.ecommerce.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/brands")
public class BrandController {
    @Autowired
    private IBrandService brandService;

    @GetMapping("")
    public Collection<BrandDTO> getPageOfBrands( @RequestParam(value = "page", defaultValue = "0") int page,
                                                  @RequestParam(value = "size", defaultValue = "5") int size) {
        Collection<BrandDTO> brandDTOS = brandService.findAll(PageRequest.of(page , size));
        return brandDTOS;
    }

}