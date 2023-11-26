package com.shelter.animalshelter.service.implement;

import com.pengrad.telegrambot.model.Update;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import com.shelter.animalshelter.exception.NotFoundException;
import com.shelter.animalshelter.model.User;
import com.shelter.animalshelter.repository.UserRepository;
import com.shelter.animalshelter.service.UserService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private static final String EXCEPTION_NOT_FOUND_USER = "Пользователь не найден!";

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getById(Long id) {
        Optional<User> optionalUser = userRepository.findByTelegramId(id);
        if (optionalUser.isEmpty()) {
            throw new NotFoundException(EXCEPTION_NOT_FOUND_USER);
        }
        return optionalUser.get();
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User update(User user) {
        User currentUser = getById(user.getTelegramId());
        EntityUtils.copyNonNullFields(user, currentUser);
        return userRepository.save(currentUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    /**
     * The method is registers the user to the DB to designate the old user.
     * It creates the object of {@link User} and sets all the fields.
     * <br>
     * {@link UserRepository} saves User to the DB.
     *
     * @param update
     */
    @Override
    public void registerUser(Update update) {
        Long chatId = update.message().chat().id();
        User user = new User();
        user.setTelegramId(chatId);
        user.setFirstName(update.message().chat().firstName());
        userRepository.save(user);
    }

    @Override
    public boolean newUser(Update update) {
        return !(userRepository.existsById(update.message().chat().id()));
    }
}

