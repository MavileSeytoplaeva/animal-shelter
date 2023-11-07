package com.shelter.animalshelter.service;

import com.shelter.animalshelter.model.animals.Dog;

import java.util.List;

public interface DogService {
    // Возвращает собаку по его id.

    Dog getById(Long id);


     // Возвращает  собакy принадлежащей хозяину с указанным идентификатором.

    List<Dog> getAllByUserId(Long id);

    // Создает новую запись о собаке в базе данных, используя переданный объект собаки.

    Dog create(Dog dog);

    // Обновляет информацию о собаке, используя переданный объект собаки.

    Dog update(Dog dog);

    // Возвращает коллекцию всех объектов собаки, находящихся в базе данных.

    List<Dog> getAll();

    // Удаляет запись о собаке с указанным id из базы данных.

    void remove(Long id);
}