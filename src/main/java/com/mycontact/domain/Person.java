package com.mycontact.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private int age;

    private String hobby;

    @NonNull
    private String bloodType;

    private String address;

    private LocalDate birthDay;

    private String job;

    @ToString.Exclude
    private String phoneNumber;

    @OneToOne
    private Block block;

    // 아래 주석 부분은 @EqualsAndHashCode 어노테이션으로 대체 가능
    // 근데 @Data 어노테이션 내부에 @EqualsAndHashCode 어노테이션이 포함되어 있음
    /*@Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        Person person = (Person) o;

        if (!person.getName().equals(this.getName())) {
            return false;
        }

        if (person.getAge() != this.getAge()) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return (name + age).hashCode();
    }*/

}
