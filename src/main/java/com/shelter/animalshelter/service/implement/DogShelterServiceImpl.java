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
    private final TelegramBot telegramBot;


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
    public List<DogShelter> getShelter() {
        return dogRepository.findAll();
    }

  //  @Override
  //  public List<Dog> getAnimal(long index) {
  //      return getSheltersId(index).getList();
  //  }


    @Override
    public String delShelter(long index) {
        String result;
        Optional<DogShelter> dogShelter = dogRepository.findById(index);
        if (dogShelter.isPresent()) {
            dogRepository.deleteById(index);
            result = "Запись удалена";
        } else {
            throw new NotFoundException("Собаки без приюта. Мы его не нашли(");
        }
        return result;
    }

    @Override
    public SendMessage getInfoAboutMe(Long chatId) {
        String messeage = dogRepository.getAboutMe();
        SendMessage message = new SendMessage(chatId, messeage);
        telegramBot.execute(message);
        return message;
    }

    @Override
    public CatShelter getAllCatShelterInfo() {
        return null;
    }

    @Override
    public DogShelter getAllDogShelterInfo() {
        return dogRepository.getAllInfo();
    }

    public SendMessage getLocation(Long chatId) {
        String messeage = dogRepository.getLocation();
        SendMessage message = new SendMessage(chatId, messeage);
        telegramBot.execute(message);
        return message;
    }

    public SendMessage getTimetable(Long chatId) {
        String messeage = dogRepository.getTimetable();
        SendMessage message = new SendMessage(chatId, messeage);
        telegramBot.execute(message);
        return message;
    }
    public SendMessage getSecurity(Long chatId) {
        String messeage = dogRepository.getSecurity();
        SendMessage message = new SendMessage(chatId, messeage);
        telegramBot.execute(message);
        return message;
    }
    public SendMessage getSafetyAdvice(Long chatId) {
        String messeage = dogRepository.getSafetyAdvice();
        SendMessage message = new SendMessage(chatId, messeage);
        telegramBot.execute(message);
        return message;
    }
}