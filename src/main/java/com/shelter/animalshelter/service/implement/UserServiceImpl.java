package com.shelter.animalshelter.service.implement;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import com.shelter.animalshelter.exception.NotFoundException;
import com.shelter.animalshelter.model.User;
import com.shelter.animalshelter.repository.UserRepository;
import com.shelter.animalshelter.service.UserService;

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
    public void delete(User user) {
        userRepository.delete(getById(user.getTelegramId()));
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(getById(id).getTelegramId());
    }
}

