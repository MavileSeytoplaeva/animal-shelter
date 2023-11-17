package com.shelter.animalshelter.service;

import com.pengrad.telegrambot.model.Update;
import com.shelter.animalshelter.model.UserInfoForContact;
import com.shelter.animalshelter.model.User;
import com.shelter.animalshelter.repository.UserInfoForContactRepository;
import com.shelter.animalshelter.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    private UserRepository userRepository;
    private UserInfoForContactRepository userInfoForContactRepository;

    public UserService(UserRepository userRepository, UserInfoForContactRepository userInfoForContactRepository) {
        this.userRepository = userRepository;
        this.userInfoForContactRepository = userInfoForContactRepository;
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

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void registerUserIfNotRegistered(Update update) {
        if (!(userRepository.existsById(update.message().chat().id()))) {
            User user = new User();

            user.setTelegramId(update.message().chat().id());
            user.setFirstName(update.message().chat().firstName());
            System.out.println(update.message().chat().id());
            System.out.println(update.message().chat().firstName());

            userRepository.save(user);
        }
    }

    public boolean newUser(Update update) {
        return !(userRepository.existsById(update.message().chat().id()));
    }
}
