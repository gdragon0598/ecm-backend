package com.huan.ecommerce.service.impl;

import com.huan.ecommerce.entity.Supplier;
import com.huan.ecommerce.repository.SupplierRepository;
import com.huan.ecommerce.service.ISupplierService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierService implements ISupplierService {
    private final SupplierRepository supplierRepository;
    @Autowired
    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Supplier findSupplierById(Long id) {
        return supplierRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Supplier cannot be found: supplier ID " + id));
    }
}
