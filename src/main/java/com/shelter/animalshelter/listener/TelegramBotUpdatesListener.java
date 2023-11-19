package com.shelter.animalshelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SetMyCommands;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.SendResponse;
import com.shelter.animalshelter.model.AnimalAdopter;
import com.shelter.animalshelter.repository.AnimalAdopterRepository;
import com.shelter.animalshelter.repository.UserRepository;
import com.shelter.animalshelter.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;


import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static liquibase.repackaged.net.sf.jsqlparser.parser.feature.Feature.insertModifierPriority;
import static liquibase.repackaged.net.sf.jsqlparser.parser.feature.Feature.update;


@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    @Value("${telegram.bot.token}")
    TelegramBot bot = new TelegramBot("${telegram.bot.token}");

    @Autowired
    private TelegramBot telegramBot;
    @Autowired
    private MenuService menuService;
    @Autowired
    private UserService userService;
    private UserRepository userRepository;

    @Autowired
    private AnimalAdopterRepository animalAdopterRepository;
    @Autowired
    private ButtonReactionService buttonReactionService;
    @Autowired
    private UpdateTextHandlerImpl updateTextHandler;

    @Autowired
    private DailyReportServiceImlp dailyReportService;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {

        try {
            updates.forEach(update -> {
                logger.info("Processing update: {}", update);
//            Объявила переменные для имени и номера чата
                if (update.callbackQuery() != null) {
                    buttonReactionService.buttonReaction(update.callbackQuery());
                } else if (update.message().text() != null) {
                    updateTextHandler.handleStartMessage(update);
                } else if (update.message().photo() != null || update.message().caption() != null) {
                    dailyReportService.postReport(update);
                }


            });
        } catch (
                Exception e) {
            logger.error(e.getMessage(), e);
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}

