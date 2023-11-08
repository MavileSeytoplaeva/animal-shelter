package com.shelter.animalshelter.service;

import com.shelter.animalshelter.exception.NotFoundException;
import com.shelter.animalshelter.model.Volunteer;
import com.shelter.animalshelter.repository.VolunteerRepository;
import com.shelter.animalshelter.service.implement.VolunteerServiceImpl;

import java.util.List;

public interface VolunteerService {
    /**
     * Создание и сохранение волонтёра в бд<br>
     * Используется метод репозитория {@link VolunteerRepository#save(Object)}
     *
     * @param volunteer Волонтёр для сохранения в бд, не может быть null
     * @return Сохранённый волонтёр
     */
    Volunteer create(Volunteer volunteer);

    /**
     * Получение волонтёра по id<br>
     * Используется метод репозитория {@link VolunteerRepository#findById(Object)}
     *
     * @param id Id волонтёра, не может быть null
     * @return Полученный из бд волонтёр
     * @throws NotFoundException Если в базе нет волонтёра с указанным id
     */
    Volunteer getById(Long id);

    /**
     * Получение волонтёра по id<br>
     * Используется метод репозитория {@link VolunteerRepository#findAll()}
     *
     * @return Список всех волонтёров
     * @throws NotFoundException Если база с волонтёрами пустая
     */
    List<Volunteer> getAll();

    /**
     * Изменение волонтёра<br>
     * Используется метод этого же сервиса {@link VolunteerServiceImpl#getById(Long)}
     *
     * @param volunteer Волонтёр, не может быть null
     * @return Изменённый волонтёр
     * @throws NotFoundException Если у передаваемого волонтёра нет id или в базе нет волонтёра с указанным id
     */
    Volunteer update(Volunteer volunteer);

    /**
     * Удаление волонтёра через объект<br>
     * Используется метод этого же сервиса {@link VolunteerServiceImpl#getById(Long)}
     *
     * @param volunteer Волонтёр, который уже есть в бд
     * @throws NotFoundException Если в базе нет волонтёра с указанным id
     */
    void delete(Volunteer volunteer);

    /**
     * Удаление волонтёра по id<br>
     * Используется метод этого же сервиса {@link VolunteerServiceImpl#getById(Long)}
     *
     * @param id Id волонтёра
     * @throws NotFoundException Если в базе нет волонтёра с указанным id
     */
    void deleteById(Long id);
}