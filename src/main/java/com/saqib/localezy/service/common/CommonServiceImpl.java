package com.saqib.localezy.service.common;

import com.saqib.localezy.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    ProductCategoryRepository productCategoryRepository;
    @Override
    public ResponseEntity<?> getAllCategories() {
        return ResponseEntity.ok( productCategoryRepository.findAll());
    }
}
