package com.shelter.animalshelter.service;
import com.shelter.animalshelter.model.User;

import java.util.List;

public interface UserService {

    // Создание и сохранение пользователя в бд

    User create(User user);

    // Получение пользователя по id

    User getById(Long id);

    // Получение выбранного в боте приюта по id пользователя

    String getShelterById(Long id);

    //return Список всех пользователей
         List<User> getAll();

    //Изменение пользователя

    User update(User user);

    // Удаление
    void delete(User user);

    //Удаление пользователя по id

    void deleteById(Long id);
}