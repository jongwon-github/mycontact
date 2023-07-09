package com.mycontact.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Data
public class PersonDto {

    private String name;

    private String hobby;

    private String address;

    private LocalDate birthday;

    private String job;

    private String phoneNumber;

}
