
package com.example.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecommerce.models.User;


@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    
}
