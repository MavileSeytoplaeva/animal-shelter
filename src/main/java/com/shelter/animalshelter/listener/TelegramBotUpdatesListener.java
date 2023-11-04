package com.shelter.animalshelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SetMyCommands;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.SendResponse;
import com.shelter.animalshelter.repository.UserRepository;
import com.shelter.animalshelter.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import java.sql.Timestamp;
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
    @Autowired
    private MenuService menuService;
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
                    // if (!checkIfUserRegistered(userChat)) {
                    SendMessage messageText = new SendMessage(chatId, "Привет я Бот, который поможет тебе обрести лучшего друга в лице животного. Пожалуйста выбери из списка приют, который тебе нужен.");
                    SendResponse response = bot.execute(messageText);
                    commandShelterList(chatId);
                    break;
                //Выбор приюта
                case "/shalter_cats":
                    menuService.getCatMenu(chatId);
                    break;
                case "/shalter_dogs":
                    menuService.getDogMenu(chatId);
                    break;
                //--------Должно быть после выбора приюта------
                case "/info_shelter":
                    menuService.getInfoAboutShelter(chatId);
                    break;
                case "/info_take_animal":
                    menuService.getInfoAboutTakeAnimal(chatId, true);
                    break;
                //-----------                   ---------------------
                case "/info_take_animal_false":
                    menuService.getInfoAboutTakeAnimal(chatId, false);
                    break;

                /*    } else {
//                        Приветствие для старого пользователя
                        System.out.println();
                    }*/


            }

        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    /**
     * Метод формирует команды для выбора приюта (кошек или собак) - начальное меню.
     * <br>
     * Используются классы {@link BotCommand}, {@link SetMyCommands}
     * <br>
     * {@link BaseResponse} формирует команды с использованием {@link StringBuilder} и в консоль выводится сообщение об успешном установлении команд или об ошибке.
     * @param chatId
     */
    private void commandShelterList(long chatId){
        List<BotCommand> botCommandList = new ArrayList<>(List.of(
                new BotCommand("/shalter_cats", "Приют для кошек"),
                new BotCommand("/shalter_dogs", "Приют для собак"),
                new BotCommand("/info_shelter", "Информация о приюте"),
                new BotCommand("/info_take_animal", "Как взять питомца"),
                //______________________------------------------________________________
                new BotCommand("/info_take_animal_false", "Приют для кошек")
                //        new BotCommand("/start","/shalter_cat or /shalter_dog"),
                //    new BotCommand("/shalter_cat","asd")
                //  new BotCommand("/shalter_dogs", "Приют для собак")
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

/*    private void registerUser(Chat chat) {
        LocalDateTime currentTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        User user = new User();

            user.setChatId(chatId);
            user.setFirstName(chat.getFirstName);
            user.setLastName(chat.getLastName());
            user.setUserName(chat.getUserName());
            user.setRegisteredAt(new Timestamp(System.currentTimeMillis()));

            userRepository.save(user);
        }

    private boolean checkIfUserRegistered(Chat chat) {
        if (userRepository.existsById(chat.id())) {
            return true;
        } else {
            registerUser(chat);
            return false;
        }
    }*/
}





