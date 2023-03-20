package com.mycontact.repository;

import com.mycontact.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    void crud() {
        Person person = new Person();
        person.setName("martin");
        person.setAge(10);
        person.setBloodType("A");

        personRepository.save(person);

        System.out.println(personRepository.findAll());

        List<Person> people = personRepository.findAll();
        assertThat(people.size()).isEqualTo(1);
        assertThat(people.get(0).getName()).isEqualTo("martin");
        assertThat(people.get(0).getAge()).isEqualTo(10);
        assertThat(people.get(0).getBloodType()).isEqualTo("A");
    }

    @Test
    void allArgsConstructor() {
        Person person = new Person(1L, "martin", 10, "reading", "A", "분당", LocalDate.of(2023, 3, 17), "programmer", "010-1111-2222");
    }

    @Test
    void hashCodeAndEquals() {
        /*
            객체의 값이 동일하더라도 person1, person2 를 equals 비교하면 다른값으로 판별되고 각 객체의 hashcode 값도 다른값으로 생성됨
            하지만 @EqualsAndHashCode 어노테이션을 추가해주면
            person1.equals(person2) -> true
            person1.hashCode(), person2.hashCode() -> 동일한 값이 return
            @Data 어노테이션 내부에 @EqualsAndHashCode 어노테이션이 포함되어 있음
        */

        Person person1 = new Person("martin", 10, "A");
        Person person2 = new Person("martin", 10, "A");
        //Person person2 = new Person("martin", 10, "B");

        System.out.println(person1.equals(person2));
        System.out.println(person1.hashCode());
        System.out.println(person2.hashCode());

        Map<Person, Integer> map = new HashMap<>();
        map.put(person1, person1.getAge());

        System.out.println(map);
        System.out.println(map.get(person2));
    }

}