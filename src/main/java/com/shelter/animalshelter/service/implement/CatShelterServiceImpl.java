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
    private final TelegramBot telegramBot;

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
    public List<CatShelter> getShelter() {
        return catRepository.findAll();
    }

    @Override
    public List<Cat> getAnimal(long index) {
        return getSheltersId(index).getList();
    }

    @Override
    public String delShelter(long index) {
        String result;
        Optional<CatShelter> catShelter = catRepository.findById(index);
        if (catShelter.isPresent()) {
            catRepository.deleteById(index);
            result = "Запись удалена";
        } else {
            throw new NotFoundException("Кошки остались без приюта. Не нашли приют");
        }
        return result;
    }

    @Override
    public SendMessage getInfoAboutMe(Long chatId) {
        String messeage = catRepository.getAboutMe();
        SendMessage message = new SendMessage(chatId, messeage);
        telegramBot.execute(message);
        return message;
    }

    @Override
    public CatShelter getAllCatShelterInfo() {
        return catRepository.getAllInfo();
    }

    @Override
    public DogShelter getAllDogShelterInfo() {
        return null;
    }

    public SendMessage getLocation(Long chatId) {
        String messeage = catRepository.getLocation();
        SendMessage message = new SendMessage(chatId, messeage);
        telegramBot.execute(message);
        return message;
    }

    public SendMessage getTimetable(Long chatId) {
        String messeage = catRepository.getTimetable();
        SendMessage message = new SendMessage(chatId, messeage);
        telegramBot.execute(message);
        return message;
    }

    public SendMessage getSecurity(Long chatId) {
        String messeage = catRepository.getSecurity();
        SendMessage message = new SendMessage(chatId, messeage);
        telegramBot.execute(message);
        return message;
    }

    public SendMessage getSafetyAdvice(Long chatId) {
        String messeage = catRepository.getSafetyAdvice();
        SendMessage message = new SendMessage(chatId, messeage);
        telegramBot.execute(message);
        return message;
    }
}