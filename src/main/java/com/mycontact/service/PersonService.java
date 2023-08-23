package com.mycontact.service;

import com.mycontact.controller.dto.PersonDto;
import com.mycontact.domain.Person;
import com.mycontact.exception.PersonNotFoundException;
import com.mycontact.exception.RenameNotPermittedException;
import com.mycontact.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person getPerson(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    public List<Person> getPeopleByName(String name) {
        return personRepository.findByName(name);
    }

    @Transactional
    public void put(PersonDto personDto) {
        Person person = new Person();
        person.set(personDto);
        person.setName(personDto.getName());
        personRepository.save(person);
    }

    @Transactional
    public void modify(Long id, PersonDto personDto) {
        Person personAtDb = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);

        if (!personAtDb.getName().equals(personDto.getName())) {
            throw new RenameNotPermittedException();
        }

        personAtDb.set(personDto);
        personRepository.save(personAtDb);
    }

    @Transactional
    public void modify(Long id, String name) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        person.setName(name);
        personRepository.save(person);
    }

    @Transactional
    public void delete(Long id) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        person.setDeleted(true); // 실제로 데이터를 삭제하는게 아니라 flag 값을 이용해 삭제 여부를 판단
        personRepository.save(person);
    }

}
