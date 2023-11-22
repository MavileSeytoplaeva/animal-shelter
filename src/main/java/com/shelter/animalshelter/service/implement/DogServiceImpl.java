package com.shelter.animalshelter.service.implement;

import com.shelter.animalshelter.model.animals.Cat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.shelter.animalshelter.exception.NotFoundException;
import com.shelter.animalshelter.model.animals.Dog;
import com.shelter.animalshelter.repository.DogRepository;
import com.shelter.animalshelter.service.DogService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DogServiceImpl implements DogService {
    private final DogRepository dogRepository;


    @Override
    public Dog create(Dog dog) {
        return dogRepository.save(dog);
    }

    @Override
    public Dog getById(Long dog_id) {
        Optional<Dog> optionalCat = dogRepository.findById(dog_id);
        if (optionalCat.isEmpty()) {
            throw new NotFoundException("Собака не найдена!");
        }
        return optionalCat.get();
    }
    @Override
    public List<Dog> getAll() {
        return dogRepository.findAll();
    }



    @Override
    public Dog update(Dog dog) {
        Optional<Dog> dogId = dogRepository.findById(dog.getDog_id());
        if (dogId.isEmpty()) {
            throw new NotFoundException("Собаки нет");
        }
        Dog currentCat = dogId.get();
        EntityUtils.copyNonNullFields(dog, currentCat);
        return dogRepository.save(currentCat);
    }

    @Override
    public void remove(Long cat_id) {
        dogRepository.deleteById(getById(cat_id).getDog_id());
    }
}