package com.shelter.animalshelter.service;

import com.shelter.animalshelter.exception.NotFoundException;
import com.shelter.animalshelter.model.Volunteer;
import com.shelter.animalshelter.repository.VolunteerRepository;
import com.shelter.animalshelter.service.implement.VolunteerServiceImpl;

import java.util.List;

public interface VolunteerService {
    //Создание и сохранение волонтёра в бд<br>

    Volunteer create(Volunteer volunteer);

    // Получение волонтёра по id<br>

    Volunteer getById(Long id);

    // Получение волонтёра по id<br>

    List<Volunteer> getAll();

    // Изменение волонтёра

    Volunteer update(Volunteer volunteer);

    //Удаление волонтёра через объект

    void delete(Volunteer volunteer);

    //Удаление волонтёра по id

    void deleteById(Long id);
}