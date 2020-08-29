package com.server.store.product.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.store.product.entity.Category;
import com.server.store.product.entity.Product;
import com.server.store.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    
    @GetMapping
    public ResponseEntity<List<Product>> ListProduct(@RequestParam(name= "categoryId", required = false )Long categoryId) {
       List<Product> products = new ArrayList<>();
        
        if (null == categoryId) {
            products = productService.listAllProduct();
            if (products.isEmpty()){
                return ResponseEntity.noContent().build();
            }   
        }else{
            products = productService.findByCategory(Category.builder().id(categoryId).build());
            if (products.isEmpty()){
                return ResponseEntity.notFound().build();
            }
        }   
        
        
        return ResponseEntity.ok(products);
    }
    
    @GetMapping(value="/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id){
        Product product = productService.getProduct(id);
        
        if (null==product) {
            return ResponseEntity.notFound().build();      
        }
        return ResponseEntity.ok(product);
    }
    
    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product, BindingResult result){
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Product createProduct = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createProduct);       
    }
    
    //@PutMapping(value = {"/id"}) --> Eror 405 PUT not Allowed
    /*???Solution???*/
    @RequestMapping(method = RequestMethod.PUT, value="/{id}")
    @ResponseBody
    public ResponseEntity<Product> updateProduct(@PathVariable("id" )Long id,@RequestBody Product product){
        product.setId(id);
        Product productDB = productService.updateProduct(product);
        if (productDB == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productDB);
    }
    
    //@DeleteMapping(value = {"/id"}) //--> Eror 405 DELETE not Allowed
    /*???Solution???*/
    @RequestMapping(method = RequestMethod.DELETE, value="/{id}")
    @ResponseBody
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id){
        Product deleteProduct = productService.deleteProduct(id);
        if (deleteProduct == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleteProduct);
    }
    
    @GetMapping(value="/{id}/stock")    
    public ResponseEntity<Product> updateStockProduct(@PathVariable("id")Long id, @RequestParam(name = "quantity", required = true) Double quantity){
        Product product = productService.updateStock(id, quantity);
         if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }
    
    private String formatMessage(BindingResult result){
        List<Map<String, String>> errors = result.getFieldErrors().stream()
            .map(err -> {
                Map<String, String> error = new HashMap<>();
                error.put(err.getField(), err.getDefaultMessage());
                return error;
            }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
            .code("01")
            .messages(errors).build();
        
        //Jacson -- Transform a Json Strong
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return jsonString;
    }
}
