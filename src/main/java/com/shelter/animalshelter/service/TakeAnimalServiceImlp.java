package com.shelter.animalshelter.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.request.SendMessage;
import com.shelter.animalshelter.model.AnimalAdopter;
import com.shelter.animalshelter.model.animals.Cat;
import com.shelter.animalshelter.model.animals.Dog;
import com.shelter.animalshelter.repository.CatRepository;
import com.shelter.animalshelter.repository.DogRepository;
import com.shelter.animalshelter.service.implement.AnimalAdopterServiceImlp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
@Service
public class TakeAnimalServiceImlp {

    private final TelegramBot telegramBot;
    private final AnimalAdopterServiceImlp animalAdopterService;
    private final DogService dogService;
    private final CatService catService;

    public TakeAnimalServiceImlp(TelegramBot telegramBot, AnimalAdopterServiceImlp animalAdopterService, DogService dogService, CatService catService) {
        this.telegramBot = telegramBot;
        this.animalAdopterService = animalAdopterService;
        this.dogService = dogService;
        this.catService = catService;
    }

    public SendMessage getInfoAboutAllCats(Long chatId) {
        List<Cat> cats = catService.getAll();
        SendMessage message = new SendMessage(chatId, cats.toString());
        telegramBot.execute(message);
        return message;
    }

    public SendMessage getInfoAboutAllDogs(Long chatId) {
        List<Dog> dogs = dogService.getAll();
        SendMessage message = new SendMessage(chatId, dogs.toString());
        telegramBot.execute(message);
        return message;
    }
    /**
     * The method is used to set true in the field tookAnimal to designate that animalAdopter had taken an animal.
     * It gets the object of animalAdopter from the DB and sets true to the field tookAnimal.
     * <br>
     * Then edits animalAdopter in the DB.
     * @param chatId
     */
    public void addTookAnimalField(Long chatId) {
        AnimalAdopter animalAdopter = animalAdopterService.findAnimalAdopter(chatId);
        animalAdopter.setTookAnimal(true);
        animalAdopterService.createAnimalAdopter(animalAdopter);
    }


}
