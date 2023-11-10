package com.shelter.animalshelter.service;

import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.request.SetMyCommands;
import com.pengrad.telegrambot.response.BaseResponse;
import com.shelter.animalshelter.model.AnimalAdopter;
import com.shelter.animalshelter.model.User;
import com.shelter.animalshelter.repository.AnimalAdopterRepository;
import com.shelter.animalshelter.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    private UserRepository userRepository;
    private AnimalAdopterRepository animalAdopterRepository;

    public UserService(UserRepository userRepository, AnimalAdopterRepository animalAdopterRepository) {
        this.userRepository = userRepository;
        this.animalAdopterRepository = animalAdopterRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public User findUser(long id) {
        return userRepository.findById(id).get();
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
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
}
