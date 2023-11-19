package com.shelter.animalshelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.*;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SetMyCommands;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.SendResponse;
//import com.shelter.animalshelter.repository.UserInfoForContactRepository;
import com.shelter.animalshelter.repository.UserRepository;
import com.shelter.animalshelter.service.MenuService;
import com.shelter.animalshelter.service.ReportServiceImlp;
//import com.shelter.animalshelter.service.UserInfoForContactServiceImlp;
import com.shelter.animalshelter.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


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

    @Autowired
    private ReportServiceImlp reportService;
    private UserRepository userRepository;

//    @Autowired
//    private UserInfoForContactServiceImlp userInfoForContactService;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);

            if (update.callbackQuery() != null) {
                System.out.println("not null");
            } else if (update.message().text() != null) {
                menuService.getStartMenuShelter(update.message().chat().id());
            } else if (update.message().photo() != null || update.message().caption() != null) {
                reportService.postReport(update);
            }

        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}





