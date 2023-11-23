package com.shelter.animalshelter.service;

import com.shelter.animalshelter.model.AnimalAdopter;

import java.util.List;

public interface AnimalAdopterService {
    AnimalAdopter createAnimalAdopter(AnimalAdopter animalAdopter);
    List<AnimalAdopter> getAllAdopters();
    List<AnimalAdopter> getAll();
    AnimalAdopter editAnimalAdopter(AnimalAdopter animalAdopter);

    AnimalAdopter addTookAnimal(AnimalAdopter animalAdopter);

    AnimalAdopter findAnimalAdopter(Long id);

    boolean existsById(Long id);
    void deleteAnimalAdopter(Long id);

    void registerAdopter(String messageText);

}
