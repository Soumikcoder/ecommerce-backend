package com.example.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.models.CartItem;
import com.example.ecommerce.models.Product;
import com.example.ecommerce.models.User;
import com.example.ecommerce.models.UserProductWrapper;

import com.example.ecommerce.repositories.CartRepo;
import com.example.ecommerce.repositories.ProductRepo;
import com.example.ecommerce.repositories.UserRepo;

@Service
public class CartServices {
    @Autowired
    UserRepo userRepo;
    @Autowired
    CartRepo cartRepo;
    @Autowired
    ProductRepo productRepo;
    public List<CartItem> getallCartItem(Integer userid) throws Exception{
        if(!userRepo.existsById(userid)){
            throw new Exception("Id doesn't exsists!");     
        }
        User user=userRepo.findById(userid).get();
        return cartRepo.findByUser(user);
    }
    public void removefromcart(Integer cartId) throws Exception{
        if (!cartRepo.existsById(cartId)) {
            throw new Exception("Cart Item doesn't exists");
        }
        CartItem cartItem=cartRepo.findById(cartId).get();
        cartRepo.delete(cartItem); 
        return;
    }
    public void addtocart(UserProductWrapper userProductWrapper) throws Exception{
        if (!userRepo.existsById(userProductWrapper.getUserId()) || !productRepo.existsById(userProductWrapper.getProductId())) {
            throw new Exception("User or Product doesn't exsits");
        }
        User user=userRepo.findById(userProductWrapper.getUserId()).get();
        Product product=productRepo.findById(userProductWrapper.getProductId()).get();
        CartItem cartItem=new CartItem(userProductWrapper.getCount(),user,product);
        cartRepo.save(cartItem);
        return ;
    }
}
