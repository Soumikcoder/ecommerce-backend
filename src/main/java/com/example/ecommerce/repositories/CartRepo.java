package com.example.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecommerce.models.CartItem;
import com.example.ecommerce.models.User;

@Repository
public interface CartRepo extends JpaRepository<CartItem,Integer>{
    public List<CartItem> findByUser(User user);
}
