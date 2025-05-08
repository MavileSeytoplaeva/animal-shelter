package com.shelter.animalshelter.controller;

import com.shelter.animalshelter.model.AnimalAdopter;
import com.shelter.animalshelter.model.animals.Cat;
import com.shelter.animalshelter.repository.CatRepository;
import com.shelter.animalshelter.service.implement.CatServiceImpl;
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

@WebMvcTest(CatController.class)
public class CatControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CatRepository catRepository;

    @SpyBean
    private CatServiceImpl catService;

    private Long cat_id = 1L;
    private String name = "Котик";
    private int age = 3;
    private boolean isHealthy = true;
    private boolean vaccinated = true;

    private Cat catObject() {
        Cat cat = new Cat();
        cat.setCat_id(cat_id);
        cat.setAge(age);
        cat.setName(name);
        cat.setVaccinated(vaccinated);
        cat.setIsHealthy(isHealthy);
        cat.setList(new ArrayList<>());
        return cat;
    }
    public JSONObject catJSON() {
        JSONObject catJSON = new JSONObject();
        catJSON.put("cat_id", cat_id);
        catJSON.put("name", name);
        catJSON.put("age", age);
        catJSON.put("isHealthy", isHealthy);
        catJSON.put("vaccinated", vaccinated);
        return catJSON;
    }

    @Test
    public void getAllTest() throws Exception {
        when(catRepository.findAll()).thenReturn(new ArrayList<>(List.of(catObject())));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/cats")
                        .content(catJSON().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void saveCatTest() throws Exception {
        when(catRepository.save(any(Cat.class))).thenReturn(catObject());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/cats")
                        .param("cat_id", String.valueOf(cat_id))
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
    public void getCatTest() throws Exception {
        when(catRepository.findById(any(Long.class))).thenReturn(Optional.of(catObject()));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/cats/1")
                        .content(catJSON().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age))
                .andExpect(jsonPath("$.isHealthy").value(isHealthy));
    }

    @Test
    public void deleteCatTestThrowNotFoundException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/cats/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }
}
