package com.mycontact.controller;

import com.mycontact.domain.Person;
import com.mycontact.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/person")
@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    @RequestMapping(value = "/{id}")
    public Person getPerson(@PathVariable Long id) {
        return personService.getPerson(id);
    }

}
