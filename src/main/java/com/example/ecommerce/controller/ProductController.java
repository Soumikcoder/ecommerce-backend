
package com.example.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.models.Product;
import com.example.ecommerce.repositories.ProductRepo;



@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepo repo;

    @PostMapping("/add")
    public ResponseEntity<Product> hello(@RequestBody Product product){
        repo.save(product);
        return new ResponseEntity<>(product,HttpStatus.CREATED);

    }
    @GetMapping("/getall")
    public ResponseEntity< List<Product> > getall(){
        return new ResponseEntity<>(repo.findAll(),HttpStatus.OK);
    }
    @GetMapping("/getproduct/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id){
        if(repo.existsById(id)){
            Product product=repo.findById(id).get();
            return new ResponseEntity<>(product,HttpStatus.FOUND);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getbycategory")
    public ResponseEntity< List<Product> > getbyparameters(@RequestParam(required = false) String category,@RequestParam(required = false) String companyName ,@RequestParam(required = false) String name){
        List<Product> products=new ArrayList<>();
        if (category != null) {
            products.addAll(repo.findByCategory(category));
        }
        if(companyName != null){
            products.addAll(repo.findByCompanyName(companyName));
        }
        if (name != null) {
            products.addAll(repo.findByName(name));
        }
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        Boolean isExists=repo.existsById(id);
        if(isExists){
            Product product=repo.findById(id).get();
            repo.delete(product);
            return new ResponseEntity<>("",HttpStatus.ACCEPTED);
        }
        else{
            return new ResponseEntity<>("",HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping ("/edit")
    public ResponseEntity<Product> editProduct(@RequestParam(required=false) Integer id,@RequestBody Product updatedProduct){
        if(id != null){
            updatedProduct.setId(id);
        }
        repo.save(updatedProduct);
        return new ResponseEntity<>(updatedProduct,HttpStatus.ACCEPTED);
  
    }
}
