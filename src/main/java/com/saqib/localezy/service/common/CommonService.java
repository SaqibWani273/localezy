package com.saqib.localezy.service.common;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface  CommonService {

    public ResponseEntity<?> getAllCategories();

}
