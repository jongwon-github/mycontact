package com.mycontact.domain.dto;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Birthday {

    private Integer yearOfBirthday;

    @Min(1)
    @Max(12)
    private Integer monthOfBirthday;

    @Min(1)
    @Max(31)
    private Integer dayOfBirthday;

    // 이 작업을 통해서 2월30일과 같이 이라는 이상한 날짜 입력값 validation 체크 가능
    public Birthday(LocalDate birthday) {
        this.yearOfBirthday = birthday.getYear();
        this.monthOfBirthday = birthday.getMonthValue();
        this.dayOfBirthday = birthday.getDayOfMonth();
    }

}
