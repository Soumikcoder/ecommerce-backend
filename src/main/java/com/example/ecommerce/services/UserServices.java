package com.example.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.models.CartItem;
import com.example.ecommerce.models.Product;
import com.example.ecommerce.models.User;
import com.example.ecommerce.repositories.CartRepo;
import com.example.ecommerce.repositories.ProductRepo;
import com.example.ecommerce.repositories.UserRepo;

@Service
public class UserServices {
    @Autowired
    CartRepo cartRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    ProductRepo productRepo;
    public void purchase(Integer userId) throws Exception{
        if (!userRepo.existsById(userId)) throw new Exception("User Doesn't Exsists!");

        User user=userRepo.findById(userId).get();
        List<CartItem> cartItems=cartRepo.findByUser(user);
        if (cartItems.size() == 0) {
            throw new Exception("Nothing in Cart!");
        }
        Long totalAmount=0L;
        for (CartItem cartItem : cartItems) {
            Product product=cartItem.getProduct();
            Long noofItemsavailaible=product.getCount();
            Long noofItemsrequired=cartItem.getCount();
            if(noofItemsavailaible < noofItemsrequired ) throw new Exception("Product not Availaible!");
            totalAmount+=noofItemsrequired*product.getPrice();
            //calculate total amount and check balance availaiblity 
            //update new product count after transaction
            // update new wallet balance
        }
        if(totalAmount > user.getWallet()) throw new Exception("Your balance is low!");
        synchronized(this){
            for (CartItem cartItem : cartItems) {
                Product product=cartItem.getProduct();
                Long noofItemsavailaible=product.getCount();
                Long noofItemsrequired=cartItem.getCount();
                product.setCount(noofItemsavailaible - noofItemsrequired);
                productRepo.save(product);
               cartRepo.delete(cartItem);
            }
            Long currentBalance=user.getWallet();
            user.setWallet(currentBalance-totalAmount);
            userRepo.save(user);
        }
    }
}
