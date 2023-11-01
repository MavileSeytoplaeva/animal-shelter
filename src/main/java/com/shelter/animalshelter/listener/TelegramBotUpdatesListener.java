package com.shelter.animalshelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SetMyCommands;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.SendResponse;
import com.shelter.animalshelter.model.User;
import com.shelter.animalshelter.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static liquibase.repackaged.net.sf.jsqlparser.parser.feature.Feature.update;


@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    @Value("${telegram.bot.token}")
    TelegramBot bot = new TelegramBot("${telegram.bot.token}");

    @Autowired
    private TelegramBot telegramBot;
    private UserRepository userRepository;

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
            Chat userChat = update.message().chat();
//            Проверяю если получили сообщение /start
            switch (update.message().text()) {
                case "/start":
                    if (!checkIfUserRegistered(userChat)) {
                        SendMessage messageText = new SendMessage(chatId, "Привет я Бот, который поможет тебе обрести лучшего друга в лице животного. Пожалуйста выбери из списка приют, который тебе нужен.");
                        SendResponse response = bot.execute(messageText);
                        commandShelterList(chatId);
                    } else {
//                        Приветствие для старого пользователя
                        System.out.println();
                    }





            }

        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    // метод регистрации пользователя


    private void commandShelterList(long chatId) {
        List<BotCommand> botCommandList = new ArrayList<>(List.of(
                new BotCommand("/cats", "Приют для кошек"),
                new BotCommand("/dogs", "Приют для собак")
        ));
        BotCommand[] botCommands = botCommandList.toArray(new BotCommand[0]);
        SetMyCommands myCommands = new SetMyCommands(botCommands);

        BaseResponse response = bot.execute(myCommands);
        if (response.isOk()) {
            System.out.println("Команды успешно установлены!");
            // Текст сообщения с командами
            StringBuilder messageText = new StringBuilder();
            for (BotCommand command : botCommandList) {
                messageText.append(command.command()).append(" - ").append(command.description()).append("\n");
            }
            SendMessage sendMessage = new SendMessage(chatId, messageText.toString());
            SendResponse sendResponse = bot.execute(sendMessage);
        } else {
            System.out.println("Ошибка установки команд: " + response.description());
        }
    }

    private void registerUser(Chat chat) {
        LocalDateTime currentTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        User user = new User();

        user.setChatId(chat.id());
        user.setFirstName(chat.firstName());
        user.setLastName(chat.lastName());
        user.setUserName(chat.username());
        user.setRegisteredAt(currentTime);

        userRepository.save(user);
    }

    private boolean checkIfUserRegistered(Chat chat) {
        if (userRepository.existsById(chat.id())) {
            return true;
        } else {
            registerUser(chat);
            return false;
        }
    }
}





