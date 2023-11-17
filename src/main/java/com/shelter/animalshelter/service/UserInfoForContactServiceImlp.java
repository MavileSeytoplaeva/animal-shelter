package com.shelter.animalshelter.service;

import com.pengrad.telegrambot.model.Update;
import com.shelter.animalshelter.model.UserInfoForContact;
import com.shelter.animalshelter.repository.UserInfoForContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserInfoForContactServiceImlp {
    private UserInfoForContactRepository userInfoForContactRepository;

    public UserInfoForContactServiceImlp(UserInfoForContactRepository userInfoForContactRepository) {
        this.userInfoForContactRepository = userInfoForContactRepository;
    }

    public UserInfoForContact createUserInfoForContact(UserInfoForContact userInfoForContact) {
        return userInfoForContactRepository.save(userInfoForContact);
    }

    public UserInfoForContact editUserInfoForContact(UserInfoForContact userInfoForContact) {
        return userInfoForContactRepository.save(userInfoForContact);
    }

    public UserInfoForContact findUserInfoForContact(Long id) {
        return userInfoForContactRepository.findById(id).get();
    }

    public void deleteUserInfoForContact(Long id) {
        userInfoForContactRepository.deleteById(id);
    }

    public List<UserInfoForContact> getAllUserInfoForContact() {
        return userInfoForContactRepository.findAll();
    }

    /**
     * Метод принимает текстовое сообщение пользователя и оттуда вычленяет номер телефона,
     * электронную почту и имя. Далее записывает данные в БД в таблицу animal_adopter.
     * <br>
     * Используются классы {@link Pattern}, {@link Matcher}
     * <br>
     * {@link UserInfoForContactRepository} интерфейс, который отвечает за сохранение данных в БД.
     *
     * @param update
     */
    public void registerUserInfoForContact(Update update) {
        String messageText = update.message().text();
        UserInfoForContact userInfoForContact = new UserInfoForContact();

        Pattern phoneNumberPattern1 = Pattern.compile("\\+7\\d{10}");
        Matcher phoneNumberMatcher = phoneNumberPattern1.matcher(messageText);

        if (phoneNumberMatcher.find()) {
            String phoneNumber = phoneNumberMatcher.group(0);
            userInfoForContact.setPhoneNumber(Long.valueOf(phoneNumber));
        }

        Pattern messageTextPattern = Pattern.compile("[а-яА-ЯёЁ]+");
        Matcher messageTextMatcher = messageTextPattern.matcher(messageText);

        if (messageTextMatcher.find()) {
            String text = messageTextMatcher.group(0);
            userInfoForContact.setName(text);
        }

        Pattern emailAddressPattern = Pattern.compile("\\w+@\\w+\\.\\w{2,}");
        Matcher emailAddressMatcher = emailAddressPattern.matcher(messageText);

        if (emailAddressMatcher.find()) {
            String emailAddress = emailAddressMatcher.group(0);
            userInfoForContact.setEmail(emailAddress);
        }

        userInfoForContactRepository.save(userInfoForContact);
    }

}
