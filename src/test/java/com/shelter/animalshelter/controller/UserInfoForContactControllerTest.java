package com.shelter.animalshelter.controller;

import com.shelter.animalshelter.model.UserInfoForContact;
import com.shelter.animalshelter.repository.UserInfoForContactRepository;
import com.shelter.animalshelter.service.UserInfoForContactServiceImlp;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserInfoForContactController.class)
class UserInfoForContactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserInfoForContactRepository userInfoForContactRepository;

    @SpyBean
    private UserInfoForContactServiceImlp userInfoForContactServiceImlp;

    private Long chatId = 1L;
    private String name = "Усыновитель";
    private Long phoneNumber = 79786514248L;
    private String email = "adopter@mail.ru";


    private UserInfoForContact animalAdopterObject() {
        UserInfoForContact userInfoForContact = new UserInfoForContact();
        userInfoForContact.setId(chatId);
        userInfoForContact.setName(name);
        userInfoForContact.setEmail(email);
        userInfoForContact.setPhoneNumber(phoneNumber);
        return userInfoForContact;
    }

    public JSONObject animalAdopterJSON() {
        JSONObject animalAdopterObject = new JSONObject();
        animalAdopterObject.put("name", name);
        animalAdopterObject.put("phoneNumber", phoneNumber);
        animalAdopterObject.put("email", email);
        animalAdopterObject.put("chatId", chatId);
        return animalAdopterObject;
    }

    @Test
    public void saveAnimalAdopterTest() throws Exception {
        when(userInfoForContactRepository.save(any(UserInfoForContact.class))).thenReturn(animalAdopterObject());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/adopter")
                        .content(animalAdopterJSON().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.phoneNumber").value(phoneNumber))
                .andExpect(jsonPath("$.email").value(email));
    }

    @Test
    public void getAnimalAdopterTest() throws Exception {
        when(userInfoForContactRepository.findById(any(Long.class))).thenReturn(Optional.of(animalAdopterObject()));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/adopter/1")
                        .content(animalAdopterJSON().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.phoneNumber").value(phoneNumber))
                .andExpect(jsonPath("$.email").value(email));
    }

    @Test
    public void deleteAnimalAdopterTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/adopter/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void getAllAdoptersTest() throws Exception {
        when(userInfoForContactRepository.findAll()).thenReturn(List.of(animalAdopterObject()));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/adopter/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}