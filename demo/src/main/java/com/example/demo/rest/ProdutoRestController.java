package com.example.demo.rest;

import com.example.demo.model.Produto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/produto")
public class ProdutoRestController {

    private static List<Produto> produtos = new ArrayList<>();

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Produto> create(@RequestBody Produto produto){
        produto.setId(UUID.randomUUID().toString());
        produtos.add(produto);
        return new ResponseEntity<>(produto, HttpStatus.CREATED);
    }

}
