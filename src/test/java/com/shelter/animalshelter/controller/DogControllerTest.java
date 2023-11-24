package com.shelter.animalshelter.controller;

import com.shelter.animalshelter.model.animals.Cat;
import com.shelter.animalshelter.model.animals.Dog;
import com.shelter.animalshelter.repository.CatRepository;
import com.shelter.animalshelter.repository.DogRepository;
import com.shelter.animalshelter.service.implement.CatServiceImpl;
import com.shelter.animalshelter.service.implement.DogServiceImpl;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Assertions;
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

@WebMvcTest(DogController.class)
public class DogControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DogRepository dogRepository;

    @SpyBean
    private DogServiceImpl dogService;

    private Long dog_id = 1L;
    private String name = "Собачка";
    private int age = 3;
    private boolean isHealthy = true;
    private boolean vaccinated = true;

    private Dog dogObject() {
        Dog dog = new Dog();
        dog.setDog_id(dog_id);
        dog.setAge(age);
        dog.setName(name);
        dog.setVaccinated(vaccinated);
        dog.setIsHealthy(isHealthy);
        dog.setList(new ArrayList<>());
        return dog;
    }

    public JSONObject dogJSON() {
        JSONObject dogJSON = new JSONObject();
        dogJSON.put("dog_id", dog_id);
        dogJSON.put("name", name);
        dogJSON.put("age", age);
        dogJSON.put("isHealthy", isHealthy);
        dogJSON.put("vaccinated", vaccinated);
        return dogJSON;
    }

    @Test
    public void getAllTest() throws Exception {
        when(dogRepository.findAll()).thenReturn(new ArrayList<>(List.of(dogObject())));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/dogs")
                        .content(dogJSON().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void saveDogTest() throws Exception {
        when(dogRepository.save(any(Dog.class))).thenReturn(dogObject());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/dogs")
                        .param("dog_id", String.valueOf(dog_id))
                        .param("name", name)
                        .param("age", String.valueOf(age))
                        .param("isHealthy", String.valueOf(isHealthy))
                        .param("vaccinated", String.valueOf(vaccinated))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age))
                .andExpect(jsonPath("$.isHealthy").value(isHealthy));
    }

    @Test
    public void getDogTest() throws Exception {
        when(dogRepository.findById(any(Long.class))).thenReturn(Optional.of(dogObject()));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/dogs/1")
                        .content(dogJSON().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age))
                .andExpect(jsonPath("$.isHealthy").value(isHealthy));

    }

    @Test
    public void deleteDogTestThrowNotFoundException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/dogs/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }
}

