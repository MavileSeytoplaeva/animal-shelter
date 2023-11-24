package com.shelter.animalshelter.service.implement;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.shelter.animalshelter.model.AnimalAdopter;
import com.shelter.animalshelter.repository.AnimalAdopterRepository;
import com.shelter.animalshelter.service.AnimalAdopterService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AnimalAdopterServiceImlp implements AnimalAdopterService {
    private AnimalAdopterRepository animalAdopterRepository;
    private final TelegramBot telegramBot;

    public AnimalAdopterServiceImlp(AnimalAdopterRepository animalAdopterRepository, TelegramBot telegramBot) {
        this.animalAdopterRepository = animalAdopterRepository;
        this.telegramBot = telegramBot;
    }

    @Override
    public AnimalAdopter createAnimalAdopter(AnimalAdopter animalAdopter) {
        return animalAdopterRepository.save(animalAdopter);
    }
    @Override
    public AnimalAdopter editAnimalAdopter(AnimalAdopter animalAdopter) {
        return animalAdopterRepository.save(animalAdopter);
    }

    @Override
    public AnimalAdopter addTookAnimal(AnimalAdopter animalAdopter) {
        return animalAdopterRepository.save(animalAdopter);
    }
    @Override
    public AnimalAdopter findAnimalAdopter(Long id) {
        return animalAdopterRepository.findById(id).get();
    }

    @Override
    public boolean existsById(Long id) {
        return animalAdopterRepository.existsById(id);
    }

    @Override
    public void deleteAnimalAdopter(Long id) {
        animalAdopterRepository.deleteById(id);
    }
    @Override
    public List<AnimalAdopter> getAllAdopters() {
        return animalAdopterRepository.findAll();
    }



}
