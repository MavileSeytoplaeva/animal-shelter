package com.shelter.animalshelter.service;
import com.pengrad.telegrambot.request.SendMessage;
import com.shelter.animalshelter.model.shelters.CatShelter;
import com.shelter.animalshelter.model.shelters.DogShelter;

import java.util.List;

public interface ShelterService<T, D> {
    // Сохранить приют в БД

    T addShelter(T shelter);

    // Обновление данных приюта

    T updateShelter(T shelter);

    //Получение приюта по id

    T getSheltersId(long id);

    //Получение приюта по имени
    T getShelterByName(String name);

    //Выдача списка приютов

    List<T> getAll();

    // Выдача списка животных приютов

  //  List<D> getAnimal(long index);


    //Удаление приюта

    void delShelter(long index);



    CatShelter getAllCatShelterInfo();

    DogShelter getAllDogShelterInfo();
}