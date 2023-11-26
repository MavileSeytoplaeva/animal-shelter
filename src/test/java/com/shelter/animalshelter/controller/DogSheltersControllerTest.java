package com.shelter.animalshelter.controller;

import com.shelter.animalshelter.model.shelters.CatShelter;
import com.shelter.animalshelter.model.shelters.DogShelter;
import com.shelter.animalshelter.repository.CatShelterRepository;
import com.shelter.animalshelter.repository.DogSheltersRepository;
import com.shelter.animalshelter.service.implement.CatShelterServiceImpl;
import com.shelter.animalshelter.service.implement.DogShelterServiceImpl;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DogSheltersController.class)
public class DogSheltersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DogSheltersRepository dogSheltersRepository;

    @SpyBean
    private DogShelterServiceImpl dogShelterService;

    private Long id = 1L;
    private String name = "Собачий приют";
    private String location = "location";
    private String timetable = "timetable";
    private String aboutMe = "about me";
    private String security = "security";
    private String safetyAdvice = "safety advice";

    private DogShelter dogShelterObject() {
        DogShelter dogShelter = new DogShelter();
        dogShelter.setDogId(1L);
        dogShelter.setId(id);
        dogShelter.setName(name);
        dogShelter.setLocation(location);
        dogShelter.setSecurity(security);
        dogShelter.setAboutMe(aboutMe);
        dogShelter.setTimetable(timetable);
        dogShelter.setSafetyAdvice(safetyAdvice);
        return dogShelter;
    }
    public JSONObject dogShelterJSON() {
        JSONObject dogShelterJSON = new JSONObject();
        dogShelterJSON.put("dog_id", 1L);
        dogShelterJSON.put("name", name);
        dogShelterJSON.put("location", location);
        dogShelterJSON.put("security", security);
        dogShelterJSON.put("aboutMe", aboutMe);
        dogShelterJSON.put("timetable", timetable);
        dogShelterJSON.put("safetyAdvice", safetyAdvice);
        return dogShelterJSON;
    }

    @Test
    public void saveDogShelterTest() throws Exception {
        when(dogSheltersRepository.save(any(DogShelter.class))).thenReturn(dogShelterObject());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/dogs/shelters")
                        .param("name", String.valueOf(name))
                        .param("location", String.valueOf(location))
                        .param("timetable", String.valueOf(timetable))
                        .param("aboutMe", String.valueOf(aboutMe))
                        .param("security", String.valueOf(security))
                        .param("safetyAdvice", String.valueOf(safetyAdvice))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.location").value(location))
                .andExpect(jsonPath("$.security").value(security));
    }
    @Test
    public void getAllTest() throws Exception {
        when(dogSheltersRepository.findAll()).thenReturn(new ArrayList<>(List.of(dogShelterObject())));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/dogs/shelters")
                        .content(dogShelterJSON().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void getDogShelterTest() throws Exception {
        when(dogSheltersRepository.findById(any(Long.class))).thenReturn(Optional.of(dogShelterObject()));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/dogs/shelters/1")
                        .content(dogShelterJSON().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.location").value(location))
                .andExpect(jsonPath("$.security").value(security));
    }
    @Test
    public void deleteDogShelterTestThrowNotFoundException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/dogs/shelters/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
}
