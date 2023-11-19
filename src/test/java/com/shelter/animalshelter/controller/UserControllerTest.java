package com.shelter.animalshelter.controller;

import com.shelter.animalshelter.model.User;
import com.shelter.animalshelter.repository.AnimalAdopterRepository;
import com.shelter.animalshelter.repository.UserRepository;
import com.shelter.animalshelter.service.implement.UserServiceImpl;
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

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private AnimalAdopterRepository animalAdopterRepository;
    @SpyBean
    private UserServiceImpl userService;

    private Long telegramId = 1L;
    private String firstName = "Mavile";

    private JSONObject userJSON() {
        JSONObject userObject = new JSONObject();
        userObject.put("firstName", firstName);
        userObject.put("telegramId", telegramId);
        return userObject;
    }

    private User userObject() {
        User user = new User();
        user.setFirstName(firstName);
        user.setTelegramId(telegramId);
        return user;
    }

    @Test
    public void getUserTest() throws Exception {
        when(userRepository.findByTelegramId(any(Long.class))).thenReturn(Optional.of(userObject()));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user/1")
                        .content(userJSON().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(firstName));
    }

    @Test
    public void saveUserTest() throws Exception {

        when(userRepository.save(any(User.class))).thenReturn(userObject());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/user")
                        .content(userJSON().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(firstName));
    }

    @Test
    public void deleteUserTest() throws Exception{
        when(userRepository.save(any(User.class))).thenReturn(userObject());
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/user/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void getAllUsersTest() throws Exception {
        when(userRepository.findAll()).thenReturn(List.of(userObject()));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}
