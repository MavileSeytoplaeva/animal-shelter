package com.shelter.animalshelter.service.implement;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.shelter.animalshelter.model.shelters.CatShelter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.shelter.animalshelter.exception.NotFoundException;
import com.shelter.animalshelter.model.animals.Dog;
import com.shelter.animalshelter.model.shelters.DogShelter;
import com.shelter.animalshelter.repository.DogSheltersRepository;
import com.shelter.animalshelter.service.ShelterService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DogShelterServiceImpl implements ShelterService<DogShelter,  Dog> {

    private final DogSheltersRepository dogRepository;

    @Override
    public DogShelter addShelter(DogShelter shelter) {
        return dogRepository.save(shelter);
    }

    @Override
    public DogShelter updateShelter(DogShelter shelter) {
        DogShelter currentShelter = getSheltersId(shelter.getId());
        EntityUtils.copyNonNullFields(shelter, currentShelter);
        return dogRepository.save(currentShelter);
    }

    @Override
    public DogShelter getSheltersId(long id) {
        Optional<DogShelter> shelterId = dogRepository.findById(id);
        if (shelterId.isEmpty()) {
            throw new NotFoundException("Приют не найден. Собаки остались без дома");
        }
        return shelterId.get();
    }

    @Override
    public DogShelter getShelterByName(String name) {
        Optional<DogShelter> shelterId = dogRepository.findByName(name);
        if (shelterId.isEmpty()) {
            throw new NotFoundException("Приют не найден. Собаки остались без дома");
        }
        return shelterId.get();
    }

    @Override
    public List<DogShelter> getAll() {
        return dogRepository.findAll();
    }



    @Override
    public void delShelter(long id) {
        dogRepository.deleteById(id);
    }

    public String getInfoAboutMe() {
        return dogRepository.getAboutMe();
    }

    @Override
    public CatShelter getAllCatShelterInfo() {
        return null;
    }

    @Override
    public DogShelter getAllDogShelterInfo() {
        return dogRepository.getAllInfo();
    }

    public String getLocation() {
        return dogRepository.getLocation();
    }

    public String getTimetable() {
        return dogRepository.getTimetable();
    }
    public String getSecurity() {
        return dogRepository.getSecurity();
    }
    public String getSafetyAdvice() {
        return dogRepository.getSafetyAdvice();
    }
}