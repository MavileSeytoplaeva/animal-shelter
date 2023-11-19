package com.shelter.animalshelter.service;
import com.pengrad.telegrambot.model.Update;
import com.shelter.animalshelter.model.User;
//import com.shelter.animalshelter.repository.UserInfoForContactRepository;
import com.shelter.animalshelter.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    // Создание и сохранение пользователя в бд

    User create(User user);

    // Получение пользователя по id

    User getById(Long id);

    // Получение выбранного в боте приюта по id пользователя

   // String getShelterById(Long id);

    //return Список всех пользователей
         List<User> getAll();

    //Изменение пользователя
    User update(User user);
    //Удаление пользователя по id
    void deleteUser(Long id);

    void registerUser(Update update);

    boolean newUser(Update update);
}