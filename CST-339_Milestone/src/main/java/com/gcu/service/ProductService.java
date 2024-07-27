package com.gcu.service;

import com.gcu.model.LoginModel;
import com.gcu.model.ProductModel;
import org.springframework.stereotype.Service;

@Service
public class ProductService {


    public boolean isProductAvailable(ProductModel productModel, Integer quantity) {
        return productModel.getStock() <= quantity;
    }
}
