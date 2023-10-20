package com.example.demo.service;

import com.example.demo.model.Product;

import java.util.List;

public interface ProductService {
    Integer saveProduct(Product p);
    void updateProduct(Product p);
    void deleteProduct(Integer id);
    Product getOneProduct(Integer id);
    List<Product> getAllProduct();

    public void updateProductCodeById(String code, Integer id);


}
