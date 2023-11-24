package com.shelter.animalshelter.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.shelter.animalshelter.model.AnimalAdopter;
import com.shelter.animalshelter.model.animals.Cat;
import com.shelter.animalshelter.model.animals.Dog;
import com.shelter.animalshelter.repository.AnimalAdopterRepository;
import com.shelter.animalshelter.service.implement.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UpdateTextHandlerImpl {
    private MenuService menuService;
    private final TelegramBot telegramBot;
    private AnimalAdopterServiceImlp animalAdopterService;
    private DogServiceImpl dogService;
    private CatServiceImpl catService;

    public UpdateTextHandlerImpl(MenuService menuService, TelegramBot telegramBot, AnimalAdopterServiceImlp animalAdopterService, DogServiceImpl dogService, CatServiceImpl catService) {
        this.menuService = menuService;
        this.telegramBot = telegramBot;
        this.animalAdopterService = animalAdopterService;
        this.dogService = dogService;
        this.catService = catService;
    }

    public SendMessage handleStartMessage(Update update) {
        Long chatId = update.message().chat().id();
        String userText = update.message().text();
        if ("/start".equals(userText)) {
            menuService.getStartMenuShelter(update);
        } else if (update.message().text().matches("(?=.*\\+7\\d{10})(?=.*[а-яА-ЯёЁ]+)(?=.*\\w+@\\w+\\.\\w{2,}).*")) {
            registerAdopter(update);
            SendMessage message = new SendMessage(chatId, "Ваши данные успешно сохранены. Наш волонтёр свяжется с вами в ближайшее время");
            telegramBot.execute(message);
            return message;
        }
        return null;
    }

    /**
     * The method receives the user's text message by update and extracts the phone number,
     * email and name. Next, it writes the data to the database in the animal_adopter table.
     * <br>
     * Classes used {@link Pattern}, {@link Matcher}
     * <br>
     * {@link AnimalAdopterRepository} interface that is responsible for saving data in the database.
     *
     * @param update
     */
    public void registerAdopter(Update update) {
        if (!(animalAdopterService.existsById(update.message().chat().id()))) {
            String messageText = update.message().text();
            AnimalAdopter animalAdopter = new AnimalAdopter();
            animalAdopter.setId(update.message().chat().id());

            Pattern phoneNumberPattern1 = Pattern.compile("\\+7\\d{10}");
            Matcher phoneNumberMatcher = phoneNumberPattern1.matcher(messageText);

            if (phoneNumberMatcher.find()) {
                String phoneNumber = phoneNumberMatcher.group(0);
                animalAdopter.setPhoneNumber(Long.valueOf(phoneNumber));
            }
            Pattern messageTextPattern = Pattern.compile("[а-яА-ЯёЁ]+");
            Matcher messageTextMatcher = messageTextPattern.matcher(messageText);

            if (messageTextMatcher.find()) {
                String text = messageTextMatcher.group(0);
                animalAdopter.setName(text);
            }
            Pattern emailAddressPattern = Pattern.compile("\\w+@\\w+\\.\\w{2,}");
            Matcher emailAddressMatcher = emailAddressPattern.matcher(messageText);

            if (emailAddressMatcher.find()) {
                String emailAddress = emailAddressMatcher.group(0);
                animalAdopter.setEmail(emailAddress);
            }
            animalAdopterService.createAnimalAdopter(animalAdopter);
        }
    }
    /**
     * The method receives the user's chat id and checks if the user had left some information about him before.
     * If not bot asks to write down information and then the method {registerAdopter} will write down the information.
     * <br>
     * If information was provided bot will ask the user to wait for the call.
     * <br>
     * {@link AnimalAdopterServiceImlp} is responsiblе for checkinf the user info int DB.
     *
     * @param chatId
     */
    public SendMessage getVolunteerHelp(Long chatId) {
        if (animalAdopterService.existsById(chatId)) {
            SendMessage message = new SendMessage(chatId, "У нас есть ваши данные для связи с вами. Наш волонтер свяжется с вами в ближайшее время.");
            telegramBot.execute(message);
            return message;
        } else {
            SendMessage message = new SendMessage(chatId, "Введите пожалуйста ваш номер, имя и электронную почту и наш волонтёр свяжется с вами в ближайшее время. Порядок написания данных не важен.");
            telegramBot.execute(message);
            return message;
        }
    }


}
