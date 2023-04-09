package com.mycontact.domain;

import com.mycontact.controller.dto.PersonDto;
import com.mycontact.domain.dto.Birthday;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.h2.util.StringUtils;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull // @RequiredArgsConstructor에 사용되는 파라미터라는 의미, null값을 체크하는데 사용되지는 않음
    @NotEmpty
    @Column(nullable = false)
    private String name;

    @NonNull
    @Min(1)
    private int age;

    private String hobby;

    @NonNull
    @NotEmpty
    @Column(nullable = false)
    private String bloodType;

    private String address;

    @Embedded
    @Valid
    private Birthday birthday;

    private String job;

    @ToString.Exclude
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true/*cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}*/)
    @ToString.Exclude
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

    public void set(PersonDto personDto) {
        if (personDto.getAge() != 0) {
            this.setAge(personDto.getAge());
        }

        if (!StringUtils.isNullOrEmpty(personDto.getHobby())) {
            this.setHobby(personDto.getHobby());
        }

        if (StringUtils.isNullOrEmpty(personDto.getBloodType())) {
            this.setBloodType(personDto.getBloodType());
        }

        if (!StringUtils.isNullOrEmpty(personDto.getAddress())) {
            this.setAddress(personDto.getAddress());
        }

        if (!StringUtils.isNullOrEmpty(personDto.getJob())) {
            this.setJob(personDto.getJob());
        }

        if (!StringUtils.isNullOrEmpty(personDto.getPhoneNumber())) {
            this.setPhoneNumber(personDto.getPhoneNumber());
        }
    }

}
