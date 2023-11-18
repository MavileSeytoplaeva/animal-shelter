package com.shelter.animalshelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SetMyCommands;
import com.pengrad.telegrambot.response.BaseResponse;
import com.shelter.animalshelter.repository.AnimalAdopterRepository;
import com.shelter.animalshelter.repository.UserRepository;
import com.shelter.animalshelter.service.ButtonReactionService;
import com.shelter.animalshelter.service.MenuService;
import com.shelter.animalshelter.service.UpdateTextHandlerImpl;
import com.shelter.animalshelter.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
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
    private UserRepository userRepository;

    @Autowired
    private AnimalAdopterRepository animalAdopterRepository;
    @Autowired
    private ButtonReactionService buttonReactionService;
    @Autowired
    private UpdateTextHandlerImpl updateTextHandler;

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
                }
//                }/*else  if (update.message().text().equals("/start")) {
//                menuService.getFirstStartMenuShelter(update.message().chat().id());*/ else if (update.message().text() != null) {
////                    messageHandler(update);
//                }


            });
        } catch (
                Exception e) {
            logger.error(e.getMessage(), e);
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

}

        /**
         * Метод формирует команды для выбора приюта (кошек или собак) - начальное меню.
         * <br>
         * Используются классы {@link BotCommand}, {@link SetMyCommands}
         * <br>
         * {@link BaseResponse} формирует команды с использованием {@link StringBuilder} и в консоль выводится сообщение об успешном установлении команд или об ошибке.
         *
         * @param chatId
         */
/*    private void commandShelterList(long chatId) {
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
    }*/

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






