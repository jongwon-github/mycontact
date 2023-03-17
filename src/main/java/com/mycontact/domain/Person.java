package com.mycontact.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private int age;

    private String hobby;

    private String bloodType;

    private String address;

    private LocalDate birthDay;

    private String job;

    @ToString.Exclude
    private String phoneNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(id, person.id) && name.equals(person.name) && Objects.equals(hobby, person.hobby) && Objects.equals(bloodType, person.bloodType) && Objects.equals(address, person.address) && Objects.equals(birthDay, person.birthDay) && Objects.equals(job, person.job) && Objects.equals(phoneNumber, person.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, hobby, bloodType, address, birthDay, job, phoneNumber);
    }

}
