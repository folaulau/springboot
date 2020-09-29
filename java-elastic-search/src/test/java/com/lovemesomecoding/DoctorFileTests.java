package com.lovemesomecoding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

        File file = new File("doctor-ut-sample.json");

        DoctorIndex[] doctors = objectMapper.readValue(file, DoctorIndex[].class);

        System.out.println("doctors.length=" + doctors.length);

        DoctorIndex doctor = doctors[0];

        System.out.println(ObjectUtils.toJson(doctor));
    }

    @Test
    public void writeJsonFile() throws JsonParseException, JsonMappingException, IOException {
        List<DoctorIndex> doctors = new ArrayList<DoctorIndex>();

        ObjectUtils.getObjectMapper().writeValue(new FileOutputStream("doctor-ut-sample.json", true), doctors);

    }

}
