package com.shelter.animalshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.shelter.animalshelter.model.User;

import java.util.Optional;


@Repository

public interface UserRepository extends JpaRepository <User, Long> {


    boolean existsById(long telegramId);


    Optional<User> findByTelegramId(Long telegramId);
}