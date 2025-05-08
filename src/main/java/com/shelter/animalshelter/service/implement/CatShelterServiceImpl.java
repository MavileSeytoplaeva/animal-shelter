package com.shelter.animalshelter.service.implement;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.shelter.animalshelter.model.shelters.DogShelter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.shelter.animalshelter.exception.NotFoundException;
import com.shelter.animalshelter.model.animals.Cat;
import com.shelter.animalshelter.model.shelters.CatShelter;
import com.shelter.animalshelter.repository.CatShelterRepository;
import com.shelter.animalshelter.service.ShelterService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CatShelterServiceImpl implements ShelterService<CatShelter, Cat> {


    private final CatShelterRepository catRepository;
    @Override
    public CatShelter addShelter(CatShelter shelter) {
        return catRepository.save(shelter);
    }

    @Override
    public CatShelter updateShelter(CatShelter catShelter) {
        CatShelter currentShelter = getSheltersId(catShelter.getId());
        EntityUtils.copyNonNullFields(catShelter, currentShelter);
        return catRepository.save(currentShelter);
    }

    @Override
    public CatShelter getSheltersId(long id) {
        Optional<CatShelter> shelterId = catRepository.findById(id);
        if (shelterId.isEmpty()) {
            throw new NotFoundException("Приют не найден. Кошки остались без дома");
        }
        return shelterId.get();
    }

    @Override
    public CatShelter getShelterByName(String name) {
        Optional<CatShelter> shelterId = catRepository.findByName(name);
        if (shelterId.isEmpty()) {
            throw new NotFoundException("Приют не найден. Кошки остались без дома");
        }
        return shelterId.get();
    }

    @Override
    public List<CatShelter> getAll() {
        return catRepository.findAll();
    }

   // @Override
   // public List<Cat> getAnimal(long index) {
   //     return getSheltersId(index).getList();
   // }

    @Override
    public void delShelter(long id) {
        catRepository.deleteById(id);
    }

    public String getInfoAboutMe() {
        return catRepository.getAboutMe();
    }

    @Override
    public CatShelter getAllCatShelterInfo() {
        return catRepository.getAllInfo();
    }

    @Override
    public DogShelter getAllDogShelterInfo() {
        return null;
    }

    public String getLocation() {
        return catRepository.getLocation();
    }

    public String getTimetable() {
        return catRepository.getTimetable();
    }

    public String getSecurity() {
        return catRepository.getSecurity();
    }

    public String getSafetyAdvice() {
        return catRepository.getSafetyAdvice();
    }
}