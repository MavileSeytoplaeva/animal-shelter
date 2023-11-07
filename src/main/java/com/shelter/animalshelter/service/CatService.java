package com.shelter.animalshelter.service;
import com.shelter.animalshelter.model.animals.Cat;

import java.util.List;

public interface CatService {


     // Возвращает кота по его id.

    Cat getById(Long id);


     //Возвращает кота, хозяину с указанным id.

    List<Cat> getAllByUserId(Long id);


     // Создает новую запись о коте в базе данных, используя переданный объект кота.

    Cat create(Cat cat);

    // Обновляет информацию о коте, используя переданный объект кота.

    Cat update(Cat cat);


    // Возвращает коллекцию всех объектов кота, находящихся в базе данных.

    List<Cat> getAll();

    // Удаляет запись о коте с указанным id из базы данных.

    void remove(Long id);
}