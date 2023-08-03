package com.example.crud.service;

import com.example.crud.entity.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

public interface ProductService {

    Product saveProduct(Product product);

    List<Product> fetchAllProducts();

    List<Product> searchProducts(String productName, float price, String status);

    Product getProductById(Long id);

    Product updateProductById(Long id, Product product);

    ResponseEntity<?> deleteProductById(Long id);
}
