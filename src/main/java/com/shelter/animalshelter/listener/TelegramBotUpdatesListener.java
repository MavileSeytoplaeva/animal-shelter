package com.shelter.animalshelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


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

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
//            Объявила переменные для имени и номера чата
            long chatId = update.message().chat().id();
            String userName = update.message().chat().firstName();
//            Проверяю если получили сообщение /start
            if (update.message().text() != null && update.message().text().equals("/start")) {
//                Отправляю приветственное сообщение
                SendMessage messageText = new SendMessage(chatId, "Привет я Бот, который поможет тебе обрести лучшего друга в лице животного. Пожалуйста выбери из списка приют, который тебе нужен. /cats чтобы узнать больше о приюте для кошек и /dogs чтобы узнать больше о приюте для собак.");
                SendResponse response = bot.execute(messageText);

            }

        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}



