package com.example.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.models.CartItem;


import com.example.ecommerce.models.UserProductWrapper;
import com.example.ecommerce.services.CartServices;
import com.example.ecommerce.services.UserServices;




@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "http://localhost:5173/")
public class CartController {
    @Autowired
    CartServices cartServices;
    @Autowired
    UserServices userServices;
    //addtocart
    @PostMapping("/add")
    public ResponseEntity<String> addtocart(@RequestBody UserProductWrapper userProductWrapper){
        try {
            cartServices.addtocart(userProductWrapper);
            return new ResponseEntity<>("Added To Cart",HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    //removefromcart
    @PostMapping("/delete/{id}")
    public ResponseEntity<String> remove(@PathVariable Integer id){
        try {
            cartServices.removefromcart(id);
            return new ResponseEntity<>("Removed from Cart",HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    //purchase
    @PostMapping("/purchase/{userId}")
    public ResponseEntity<String> purchase(@PathVariable Integer userId){
        try {
            userServices.purchase(userId);
            return new ResponseEntity<>("Payment Successfull!",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage() ,HttpStatus.BAD_REQUEST);
        }
    }

    //getcart
    @GetMapping("/get/{userid}")
    public ResponseEntity<List<CartItem>> getcart(@PathVariable Integer userid ){
        try {
            return new ResponseEntity<>(cartServices.getallCartItem(userid),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
