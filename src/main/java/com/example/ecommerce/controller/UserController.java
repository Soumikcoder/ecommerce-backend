
package com.example.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.ecommerce.models.User;
import com.example.ecommerce.models.UserWrapper;
import com.example.ecommerce.repositories.UserRepo;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:5173/")
public class UserController {
    @Autowired
    UserRepo userRepo;

    @PostMapping("/create")
    public ResponseEntity <User> createuser(@RequestBody UserWrapper userWrapper ){
        User user=new User();
        user.setUsername(userWrapper.getUsername());
        user.setCartItem(new ArrayList<>());
        user.setWallet((long)0);
        userRepo.save(user);
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteuser(@PathVariable Integer id){
         Boolean isExists=userRepo.existsById(id);
        if(isExists){
            User user=userRepo.findById(id).get();
            userRepo.delete(user);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<User> getdetails(@PathVariable Integer id){
        if(userRepo.existsById(id)){
            return new ResponseEntity<>(userRepo.findById(id).get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/getall")
    public ResponseEntity<List<User>> getalluser(){
        return new ResponseEntity<>(userRepo.findAll(),HttpStatus.OK);
    }
    @PutMapping("/addmoney/{id}")
    public ResponseEntity<User> addmoney(@RequestBody Long amount,@PathVariable Integer id){
        if(id!=null && amount !=null && userRepo.existsById(id)){
            User user=userRepo.findById(id).get();
            user.setWallet(amount);
            userRepo.save(user);
            return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @PutMapping("/edit")
    public ResponseEntity<UserWrapper> edituser(@RequestBody UserWrapper userWrapper){
        if(userRepo.existsById(userWrapper.getUserId())){
            User user=userRepo.findById(userWrapper.getUserId()).get();
            user.setUsername(userWrapper.getUsername());
            userRepo.save(user);
        }
        else{
            User user=new User();
            user.setUsername(userWrapper.getUsername());
            user.setCartItem(new ArrayList<>());
            user.setWallet((long)0);
            userRepo.save(user);
        }
        return new ResponseEntity<>(userWrapper,HttpStatus.ACCEPTED);
  
    }

}
