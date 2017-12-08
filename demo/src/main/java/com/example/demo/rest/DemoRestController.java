package com.example.demo.rest;

import com.example.demo.model.DemoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demo")
public class DemoRestController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> get(){
        return new ResponseEntity<>(new DemoResponse(true), HttpStatus.OK);
    }

}
