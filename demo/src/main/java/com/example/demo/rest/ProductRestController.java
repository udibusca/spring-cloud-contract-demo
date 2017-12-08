package com.example.demo.rest;

import com.example.demo.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductRestController {

    private static List<Product> products = new ArrayList<>();

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Product> create(@RequestBody Product product){
        product.setId(UUID.randomUUID().toString());
        products.add(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

}
