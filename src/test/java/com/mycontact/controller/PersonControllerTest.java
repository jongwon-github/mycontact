package com.mycontact.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycontact.controller.dto.PersonDto;
import com.mycontact.domain.Person;
import com.mycontact.domain.dto.Birthday;
import com.mycontact.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.NestedServletException;

import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@Slf4j
@SpringBootTest
class PersonControllerTest {

    @Autowired
    private PersonController personController;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeEach
    void beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
    }

    @Test
    void getPerson() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/person/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void postPerson() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"martin2\", \"bloodType\": \"A\"}"))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void modifyPerson() throws Exception {
        PersonDto dto = new PersonDto().of("martin", "programming", "판교", LocalDate.now(), "programmer", "010-1111-2222");

        mockMvc.perform(MockMvcRequestBuilders.put("/api/person/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(toJsonString(dto)))
                .andDo(print())
                .andExpect(status().isOk());

        Person result = personRepository.findById(1L).get();
        assertAll(
                () -> assertThat(result.getName()).isEqualTo("martin"),
                () -> assertThat(result.getHobby()).isEqualTo("programming"),
                () ->assertThat(result.getAddress()).isEqualTo("판교"),
                () ->assertThat(result.getBirthday()).isEqualTo(Birthday.of(LocalDate.now())),
                () ->assertThat(result.getJob()).isEqualTo("programmer"),
                () ->assertThat(result.getPhoneNumber()).isEqualTo("010-1111-2222")
        );
//        assertThat(result.getName()).isEqualTo("martin");
//        assertThat(result.getHobby()).isEqualTo("programming");
//        assertThat(result.getAddress()).isEqualTo("판교");
//        assertThat(result.getBirthday()).isEqualTo(Birthday.of(LocalDate.now().plusDays(1)));
//        assertThat(result.getJob()).isEqualTo("programmer");
//        assertThat(result.getPhoneNumber()).isEqualTo("010-1111-2222");
    }

    @Test
    void modifyPersonIfNameIsDifferent() throws Exception {
        PersonDto dto = new PersonDto().of("james", "programming", "판교", LocalDate.now(), "programmer", "010-1111-2222");

        assertThrows(NestedServletException.class, () -> mockMvc.perform(MockMvcRequestBuilders.put("/api/person/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(toJsonString(dto)))
                .andDo(print())
                .andExpect(status().isOk()));
    }

    @Test
    void modifyName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/api/person/1")
                        .characterEncoding("utf-8")
                        .param("name", "martinModified"))
                .andDo(print())
                .andExpect(status().isOk());

        // Optional.get() 호출 전에 Optional 객체가 값을 가지고 있음을 확실히 할 것
        assertThat(personRepository.findById(1L).get().getName()).isEqualTo("martinModified");
    }

    @Test
    void deletePerson() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/person/1"))
                .andDo(print())
                .andExpect(status().isOk());

        assertThat(personRepository.findPeopleDeleted().stream().anyMatch(person -> person.getId().equals((1L))));
    }

    @Test
    void checkJsonString() throws JsonProcessingException {
        PersonDto dto = new PersonDto();
        dto.setName("martin");
        dto.setBirthday(LocalDate.now());
        dto.setAddress("판교");

        System.out.println(">>> " + toJsonString(dto));
    }

    private String toJsonString(PersonDto personDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(personDto);
    }

}