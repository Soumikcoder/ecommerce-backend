
package com.example.ecommerce.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="userentity")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(unique = true)
    private String username;
    private Long wallet;
    @OneToMany
    private List<CartItem> cartItem;
    
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Long getWallet() {
        return wallet;
    }
    public void setWallet(Long wallet) {
        this.wallet = wallet;
    }
    public List<CartItem> getCartItem() {
        return cartItem;
    }
    public void setCartItem(List<CartItem> cartItem) {
        this.cartItem = cartItem;
    }

    
}
