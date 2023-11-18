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
    public AnimalAdopter findAnimalAdopter(Long id) {
        return animalAdopterRepository.findById(id).get();
    }
    @Override
    public void deleteAnimalAdopter(Long id) {
        animalAdopterRepository.deleteById(id);
    }
    @Override
    public List<AnimalAdopter> getAllAdopters() {
        return animalAdopterRepository.findAll();
    }
    /**
     * Метод принимает текстовое сообщение пользователя и оттуда вычленяет номер телефона,
     * электронную почту и имя. Далее записывает данные в БД в таблицу animal_adopter.
     * <br>
     * Используются классы {@link Pattern}, {@link Matcher}
     * <br>
     * {@link AnimalAdopterRepository} интерфейс, который отвечает за сохранение данных в БД.
     *
     * @param messageText
     */
    public void registerAdopter(String messageText) {
        AnimalAdopter animalAdopter = new AnimalAdopter();

        Pattern phoneNumberPattern1 = Pattern.compile("\\+7\\d{10}");
        Matcher phoneNumberMatcher = phoneNumberPattern1.matcher(messageText);

        if (phoneNumberMatcher.find()) {
            String phoneNumber = phoneNumberMatcher.group(0);
//            System.out.println("Номер телефона: " + phoneNumber);
            animalAdopter.setPhoneNumber(Long.valueOf(phoneNumber));

        }

        Pattern messageTextPattern = Pattern.compile("[а-яА-ЯёЁ]+");
        Matcher messageTextMatcher = messageTextPattern.matcher(messageText);

        if (messageTextMatcher.find()) {
            String text = messageTextMatcher.group(0);
            System.out.println("Текст сообщения: " +  text);
            animalAdopter.setName(text);
        }

        Pattern emailAddressPattern = Pattern.compile("\\w+@\\w+\\.\\w{2,}");
        Matcher emailAddressMatcher = emailAddressPattern.matcher(messageText);

        if (emailAddressMatcher.find()) {
            String emailAddress = emailAddressMatcher.group(0);
            System.out.println("Электронный адрес: " + emailAddress);
            animalAdopter.setEmail(emailAddress);
        }
        animalAdopterRepository.save(animalAdopter);
    }

    public SendMessage getVolunteerHelp(Update update) {
        if (animalAdopterRepository.existsById(update.message().chat().id())) {
            SendMessage message = new SendMessage(update.message().chat().id(), "У нас есть ваши данные для связи с вами. Наш волонтер свяжется с вами в ближайшее время.");
            telegramBot.execute(message);
            return message;
        } else {
            SendMessage message = new SendMessage(update.message().chat().id(), "Введите пожалуйста ваш номер, имя и электронную почту и наш волонтёр свяжется с вами в ближайшее время. Порядок написания данных не важен.");
            telegramBot.execute(message);
            return message;
        }
    }
}
