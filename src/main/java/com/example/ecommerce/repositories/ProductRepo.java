package com.example.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecommerce.models.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer>{
    
    List<Product> findByCategory(String category);
    List<Product> findByName(String name);
    List<Product> findByCompanyName(String companyName);
}
