package com.mycontact.domain;

import com.mycontact.controller.dto.PersonDto;
import com.mycontact.domain.dto.Birthday;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.h2.util.StringUtils;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;

import java.time.LocalDate;

@Entity // @Entity 는 @Valid 어노테이션이 없어도 @Min 등과 같은 validation 체크가 가능한거 같음
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Where(clause = "deleted = false")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull // Entity 매핑 시점에서 클라이언트에 400에러 리턴, "", " " 허용
    @NotEmpty
    @Column(nullable = false)
    private String name;

    private String hobby;

    private String address;

    @Embedded
    private Birthday birthday;

    private String job;

    private String phoneNumber;

    @ColumnDefault("0")
    private boolean deleted;

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
        if (!StringUtils.isNullOrEmpty(personDto.getHobby())) {
            this.setHobby(personDto.getHobby());
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

        if (personDto.getBirthday() != null) {
            this.setBirthday(Birthday.of(personDto.getBirthday()));
        }
    }

    public Integer getAge() {
        if (this.birthday != null) {
            return LocalDate.now().getYear() - this.birthday.getYearOfBirthday() + 1;
        } else {
            return null;
        }
    }

    public boolean isBirthdayToday() {
        return LocalDate.now().equals(LocalDate.of(this.birthday.getYearOfBirthday(), this.birthday.getMonthOfBirthday(), this.birthday.getDayOfBirthday()));
    }

}
