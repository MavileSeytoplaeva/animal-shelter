package com.shelter.animalshelter.controller;

import com.shelter.animalshelter.model.animals.Cat;
import com.shelter.animalshelter.model.shelters.CatShelter;
import com.shelter.animalshelter.repository.CatRepository;
import com.shelter.animalshelter.repository.CatShelterRepository;
import com.shelter.animalshelter.service.implement.CatServiceImpl;
import com.shelter.animalshelter.service.implement.CatShelterServiceImpl;
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

@WebMvcTest(CatSheltersController.class)
public class CatSheltersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CatShelterRepository catShelterRepository;

    @SpyBean
    private CatShelterServiceImpl catShelterService;

    private Long id = 1L;
    private String name = "Кошачий приют";
    private String location = "location";
    private String timetable = "timetable";
    private String aboutMe = "about me";
    private String security = "security";
    private String safetyAdvice = "safety advice";

    private CatShelter catShelterObject() {
        CatShelter catShelter = new CatShelter();
        catShelter.setCatId(1L);
        catShelter.setId(id);
        catShelter.setName(name);
        catShelter.setLocation(location);
        catShelter.setSecurity(security);
        catShelter.setAboutMe(aboutMe);
        catShelter.setTimetable(timetable);
        catShelter.setSafetyAdvice(safetyAdvice);
        return catShelter;
    }
    public JSONObject catShelterJSON() {
        JSONObject catShelterJSON = new JSONObject();
        catShelterJSON.put("cat_id", 1L);
        catShelterJSON.put("name", name);
        catShelterJSON.put("location", location);
        catShelterJSON.put("security", security);
        catShelterJSON.put("aboutMe", aboutMe);
        catShelterJSON.put("timetable", timetable);
        catShelterJSON.put("safetyAdvice", safetyAdvice);
        return catShelterJSON;
    }


    @Test
    public void saveCatShelterTest() throws Exception {
        when(catShelterRepository.save(any(CatShelter.class))).thenReturn(catShelterObject());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/cats/shelters")
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
        when(catShelterRepository.findAll()).thenReturn(new ArrayList<>(List.of(catShelterObject())));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/cats/shelters")
                        .content(catShelterJSON().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void getCatShelterTest() throws Exception {
        when(catShelterRepository.findById(any(Long.class))).thenReturn(Optional.of(catShelterObject()));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/cats/shelters/1")
                        .content(catShelterJSON().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.location").value(location))
                .andExpect(jsonPath("$.security").value(security));
    }
    @Test
    public void deleteCatShelterTestThrowNotFoundException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/cats/shelters/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
}
