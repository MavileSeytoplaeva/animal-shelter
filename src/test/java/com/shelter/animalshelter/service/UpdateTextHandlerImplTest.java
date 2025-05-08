package com.shelter.animalshelter.service;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.shelter.animalshelter.repository.AnimalAdopterRepository;
import com.shelter.animalshelter.service.implement.AnimalAdopterServiceImlp;
import liquibase.pro.packaged.S;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class UpdateTextHandlerImplTest {
    @Mock
    private AnimalAdopterRepository animalAdopterRepository;
    @Mock
    private final AnimalAdopterServiceImlp animalAdopterServiceImlp = new AnimalAdopterServiceImlp(animalAdopterRepository);

    @InjectMocks
    private UpdateTextHandlerImpl updateTextHandler;


    @Test
    public void getNameFromMessageTest() {
        String actualName = updateTextHandler.getNameFromMessage("юзер +79786514242 user@mail.ru");
        Assertions.assertEquals("юзер", actualName);
    }

    @Test
    public void getPhoneFromMessageTest() {
        Long actualPhone = updateTextHandler.getPhoneFromMessage("юзер +79786514242 user@mail.ru");
        Assertions.assertEquals(79786514242L, actualPhone);
    }

    @Test
    public void getEmailFromMessageTest() {
        String actualEmail = updateTextHandler.getEmailFromMessage("юзер +79786514242 user@mail.ru");
        Assertions.assertEquals( "user@mail.ru", actualEmail);
    }
}

