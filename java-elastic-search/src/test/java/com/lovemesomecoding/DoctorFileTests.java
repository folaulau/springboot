package com.lovemesomecoding;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovemesomecoding.index.doctor.DoctorIndex;
import com.lovemesomecoding.utils.ObjectUtils;

public class DoctorFileTests {

    @Test
    public void readJsonFile() throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper objectMapper = ObjectUtils.getObjectMapper();

        File file = new File("doctor-ut.json");

        DoctorIndex[] doctors = objectMapper.readValue(file, DoctorIndex[].class);

        System.out.println("doctors.length=" + doctors.length);

        DoctorIndex doctor = doctors[0];

        System.out.println(ObjectUtils.toJson(doctor));
    }

}
