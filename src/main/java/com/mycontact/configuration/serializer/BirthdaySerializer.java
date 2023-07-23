package com.mycontact.configuration.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.mycontact.domain.dto.Birthday;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Serializer는 Java 객체를 JSON 데이터로 변환할 때 사용되고, Deserializer는 JSON 데이터를 Java 객체로 변환할 때 사용
 */
public class BirthdaySerializer extends JsonSerializer<Birthday> {

    @Override
    public void serialize(Birthday value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value != null) gen.writeObject(LocalDate.of(value.getYearOfBirthday(), value.getMonthOfBirthday(), value.getDayOfBirthday()));
    }

}
