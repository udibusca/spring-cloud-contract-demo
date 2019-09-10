package com.exemplo.demo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.exemplo.demo.model.Pessoa;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pessoa")
public class PessoaRestController {

    private static List<Pessoa> pessoas;

    static {
        pessoas = new ArrayList<>();
        pessoas.add(new Pessoa(UUID.randomUUID().toString(), UUID.randomUUID().toString(), "abc@abc.com"));
        pessoas.add(new Pessoa(UUID.randomUUID().toString(), UUID.randomUUID().toString(), "abc@abc.com"));
        pessoas.add(new Pessoa(UUID.randomUUID().toString(), UUID.randomUUID().toString(), "abc@abc.com"));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Pessoa> create(@RequestBody Pessoa pessoa){
        pessoa.setId(UUID.randomUUID().toString());
        pessoas.add(pessoa);
        return new ResponseEntity<>(pessoa, HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> readAll(){
        return new ResponseEntity<>(pessoas, HttpStatus.OK);
    }

}
