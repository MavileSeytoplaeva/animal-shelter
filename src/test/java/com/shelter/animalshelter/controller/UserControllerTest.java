package com.shelter.animalshelter.controller;

import com.shelter.animalshelter.model.User;
import com.shelter.animalshelter.repository.AnimalAdopterRepository;
import com.shelter.animalshelter.repository.UserRepository;
import com.shelter.animalshelter.service.UserService;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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
    private UserService userService;

    @Test
    public void getUserTest() throws Exception {
        String dateString = "05.11.2023 19:45";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        LocalDateTime date = LocalDateTime.parse(dateString, formatter);
        Long chatId = 1L;
        String userName = "Mavileshka";
        String firstName = "Mavile";
        String lastName = "se";

        JSONObject userObject = new JSONObject();
        userObject.put("userName", userName);
        userObject.put("firstName", firstName);
        userObject.put("lastName", lastName);
        userObject.put("registeredAt", date);
        userObject.put("chatId", chatId);

        User user = new User();
        user.setChatId(chatId);
        user.setUserName(userName);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRegisteredAt(date);

//        when(userRepository.save(user)).thenReturn(user);
        when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(user));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user/1")
                        .content(userObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value(userName))
                .andExpect(jsonPath("$.firstName").value(firstName));
    }

    @Test
    public void saveUserTest() throws Exception {
        String dateString = "05.11.2023 19:45";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        LocalDateTime date = LocalDateTime.parse(dateString, formatter);
        Long chatId = 1L;
        String userName = "Mavileshka";
        String firstName = "Mavile";
        String lastName = "se";

        JSONObject userObject = new JSONObject();
        userObject.put("userName", userName);
        userObject.put("firstName", firstName);
        userObject.put("lastName", lastName);
        userObject.put("registeredAt", date);

        User user = new User();
        user.setChatId(chatId);
        user.setUserName(userName);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRegisteredAt(date);

        when(userRepository.save(any(User.class))).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/user")
                        .content(userObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value(userName))
                .andExpect(jsonPath("$.firstName").value(firstName));
    }

    @Test
    public void deleteUserTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/user/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
}
