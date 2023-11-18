package com.shelter.animalshelter.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.shelter.animalshelter.model.AnimalAdopter;
import com.shelter.animalshelter.repository.AnimalAdopterRepository;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UpdateTextHandlerImpl {
    private MenuService menuService;
    private final TelegramBot telegramBot;

    private AnimalAdopterRepository animalAdopterRepository;

    public UpdateTextHandlerImpl(MenuService menuService, TelegramBot telegramBot, AnimalAdopterRepository animalAdopterRepository) {
        this.menuService = menuService;
        this.telegramBot = telegramBot;
        this.animalAdopterRepository = animalAdopterRepository;
    }

    public void handleStartMessage(Update update) {
        Long chatId = update.message().chat().id();
        String userText = update.message().text();

        if ("/start".equals(userText)) {
            menuService.getStartMenuShelter(update);
        }
        if (update.message().text().matches("(?=.*\\+7\\d{10})(?=.*[а-яА-ЯёЁ]+)(?=.*\\w+@\\w+\\.\\w{2,}).*")) {
            registerAdopter(update);
        }
    }

    public void registerAdopter(Update update) {
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
        animalAdopterRepository.save(animalAdopter);
    }
    public SendMessage getVolunteerHelp(Long chatId) {
        if (animalAdopterRepository.existsById(chatId)) {
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
