package com.shelter.animalshelter.service;

import com.shelter.animalshelter.model.AnimalAdopter;

import java.util.List;

public interface AnimalAdopterService {
    AnimalAdopter createAnimalAdopter(AnimalAdopter animalAdopter);
    AnimalAdopter editAnimalAdopter(AnimalAdopter animalAdopter);
    AnimalAdopter findAnimalAdopter(Long id);
    void deleteAnimalAdopter(Long id);
    List<AnimalAdopter> getAllAdopters();

    void registerAdopter(String messageText);

}
