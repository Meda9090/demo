package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

// Query - Запрос
// @Modifying - изменять, регулировать
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Modifying
    @Query("UPDATE Product SET prodCode=:code WHERE prodId=:id")
    public void updateProductCodeById(String code, Integer id);
}
