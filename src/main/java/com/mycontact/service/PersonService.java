package com.mycontact.service;

import com.mycontact.controller.dto.PersonDto;
import com.mycontact.domain.Person;
import com.mycontact.domain.dto.Birthday;
import com.mycontact.repository.BlockRepository;
import com.mycontact.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private BlockRepository blockRepository;

    public List<Person> getPeopleExcludeBlocks() {
//        List<Person> people = personRepository.findAll();
//        List<Block> blocks = blockRepository.findAll();
//        List<String> blockNames = blocks.stream().map(Block::getName).collect(Collectors.toList());
//        return people.stream().filter(person -> !blockNames.contains(person.getName())).collect(Collectors.toList());
//        return people.stream().filter(person -> person.getBlock() == null).collect(Collectors.toList());
        return personRepository.findByBlockIsNull();
    }

    public Person getPerson(Long id) {
        Person person = personRepository.findById(id).orElse(null);
        log.info("Person : {}", person);
        return person;
    }

    public List<Person> getPeopleByName(String name) {
//        List<Person> people = personRepository.findAll();
//        return people.stream().filter(person -> person.getName().equals(name)).collect(Collectors.toList());
        return personRepository.findByName(name);
    }

    @Transactional
    public void put(Person person) {
        personRepository.save(person);
    }

    @Transactional
    public void modify(Long id, PersonDto personDto) {
        Person personAtDb = personRepository.findById(id).orElseThrow(() -> new RuntimeException("아이디가 존재하지 않습니다."));

        if (!personDto.getName().equals(personDto.getName())) {
            throw new RuntimeException("이름이 아릅니다.");
        }

        personAtDb.set(personDto);

        personRepository.save(personAtDb);
    }

    @Transactional
    public void modify(Long id, String name) {
        Person person = personRepository.findById(id).orElseThrow(() -> new RuntimeException("아이디가 존재하지 않습니다."));

        person.setName(name);

        personRepository.save(person);
    }

}
