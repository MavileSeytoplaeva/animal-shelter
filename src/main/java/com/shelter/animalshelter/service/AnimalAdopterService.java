package com.shelter.animalshelter.service;

import com.shelter.animalshelter.model.AnimalAdopter;
import com.shelter.animalshelter.repository.AnimalAdopterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalAdopterService {
    private AnimalAdopterRepository animalAdopterRepository;

    public AnimalAdopterService(AnimalAdopterRepository animalAdopterRepository) {
        this.animalAdopterRepository = animalAdopterRepository;
    }

    public AnimalAdopter createAnimalAdopter(AnimalAdopter animalAdopter) {
        return animalAdopterRepository.save(animalAdopter);
    }

    public AnimalAdopter editAnimalAdopter(AnimalAdopter animalAdopter) {
        return animalAdopterRepository.save(animalAdopter);
    }

    public AnimalAdopter findAnimalAdopter(Long id) {
        return animalAdopterRepository.findById(id).get();
    }

    public void deleteAnimalAdopter(Long id) {
        animalAdopterRepository.deleteById(id);
    }

    public List<AnimalAdopter> getAllAdopters() {
        return animalAdopterRepository.findAll();
    }
}
