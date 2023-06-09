package com.mycontact.repository;

import com.mycontact.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        person.setName("john");
        personRepository.save(person);

        List<Person> result = personRepository.findByName("john");
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getName()).isEqualTo("john");
        //assertThat(result.get(0).getAge()).isEqualTo(10);
        //assertThat(result.get(0).getBloodType()).isEqualTo("A");
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
        Person person1 = new Person("martin");
        Person person2 = new Person("martin");
        //Person person2 = new Person("martin", 10, "B");

        System.out.println(person1.equals(person2));
        System.out.println(person1.hashCode());
        System.out.println(person2.hashCode());

        Map<Person, Integer> map = new HashMap<>();
        map.put(person1, person1.getAge());

        System.out.println(map);
        System.out.println(map.get(person2));
    }

    @Test
    void findByBirthdayBetween() {
        List<Person> result = personRepository.findByMonthOfBirthday(8);
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getName()).isEqualTo("martin");
        assertThat(result.get(1).getName()).isEqualTo("sophia");
    }

}