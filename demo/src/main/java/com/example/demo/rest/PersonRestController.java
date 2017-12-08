package com.example.demo.rest;

import com.example.demo.model.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/person")
public class PersonRestController {

    private static List<Person> persons;

    static {
        persons = new ArrayList<>();
        persons.add(new Person(UUID.randomUUID().toString(), UUID.randomUUID().toString(), "abc@abc.com"));
        persons.add(new Person(UUID.randomUUID().toString(), UUID.randomUUID().toString(), "abc@abc.com"));
        persons.add(new Person(UUID.randomUUID().toString(), UUID.randomUUID().toString(), "abc@abc.com"));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Person> create(@RequestBody Person person){
        person.setId(UUID.randomUUID().toString());
        persons.add(person);
        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> readAll(){
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

}
