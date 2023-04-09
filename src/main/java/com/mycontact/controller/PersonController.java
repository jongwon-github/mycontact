package com.mycontact.controller;

import com.mycontact.controller.dto.PersonDto;
import com.mycontact.domain.Person;
import com.mycontact.repository.PersonRepository;
import com.mycontact.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/person")
@RestController
@Slf4j
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    @GetMapping
    @RequestMapping(value = "/{id}")
    public Person getPerson(@PathVariable Long id) {
        return personService.getPerson(id);
    }

    @PostMapping
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity postPerson(@RequestBody Person person) {
        personService.put(person);
        log.info("person -> {}", personRepository.findAll());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public void modifyPerson(@PathVariable Long id, @RequestBody PersonDto personDto) {
        personService.modify(id, personDto);
        log.info("person -> {}", personRepository.findAll());
    }

    // person 정보 중 일부만 변경하는 경우, patch 사용
    @PatchMapping
    public void modifyPerson(@PathVariable Long id, String name) {
        personService.modify(id, name);
        log.info("person -> {}", personRepository.findAll());
    }

}
