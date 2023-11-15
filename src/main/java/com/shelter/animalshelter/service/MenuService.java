package com.shelter.animalshelter.service;


import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.shelter.animalshelter.util.KeyboardUtil;
import org.springframework.stereotype.Service;

import static com.shelter.animalshelter.util.CallbackDataRequest.*;


@Service
public class MenuService {

    private final TelegramBot telegramBot;
    private final KeyboardUtil keyboardUtil;

    public MenuService(TelegramBot telegramBot, KeyboardUtil keyboardUtil) {
        this.telegramBot = telegramBot;
        this.keyboardUtil = keyboardUtil;
    }

    //    @Override
//    public SendMessage getFirstStartMenuShelter(Long chatId) {
//
//        InlineKeyboardMarkup keyboard = keyboardUtil.setKeyboard(CAT, DOG);
//
//        SendMessage sendMessage = new SendMessage(chatId, "Добро пожаловать в наш приют! Если вы ищете верного и преданного друга, то пришли по адресу! " +
//                " Вы хотите подружиться с кошкой или с " +
//                "собакой? Пожалуйста, выберите подходящий приют, чтобы узнать больше.").replyMarkup(keyboard);
//        telegramBot.execute(sendMessage);
//        return sendMessage;
//    }

//  /*  // @Override
    public SendMessage getStartMenuShelter(Long chatId) {

        InlineKeyboardMarkup keyboard = keyboardUtil.setKeyboard(CAT, DOG);

        SendMessage sendMessage = new SendMessage(chatId, "Рады видеть Вас снова! Выберите приют").replyMarkup(keyboard);
        telegramBot.execute(sendMessage);
        return sendMessage;
    }
//    }*/




    public SendMessage getCatMenu(Long chatId) {
        InlineKeyboardMarkup keyboard = keyboardUtil.setKeyboard(
                GENERAL_SHELTER_INFO,
                HOW_TO_TAKE_ANIMAL,
                REPORT_ANIMAL,
                TAKE_CAT,
                VOLUNTEER);
        SendMessage sendMessage = new SendMessage(chatId, "Вы выбрали приют для кошек. Что Вас интересует?").replyMarkup(keyboard);
        telegramBot.execute(sendMessage);
        return sendMessage;
    }


    public SendMessage getDogMenu(Long chatId) {

        InlineKeyboardMarkup keyboard = keyboardUtil.setKeyboard(
                GENERAL_SHELTER_INFO,
                HOW_TO_TAKE_ANIMAL,
                REPORT_ANIMAL,
                TAKE_DOG,
                VOLUNTEER);
        SendMessage sendMessage = new SendMessage(chatId, "Вы выбрали приют для собак. Что Вас интересует?").replyMarkup(keyboard);
        telegramBot.execute(sendMessage);
        return sendMessage;
    }

    public SendMessage getInfoAboutShelter(Long chatId) {

        InlineKeyboardMarkup keyboard = keyboardUtil.setKeyboard(
                ABOUT_SHELTER,
                CONTACT_SHELTER,
                SAFETY_CONTACT_FOR_CAR_PASS,
                SAFETY_IN_SHELTER_TERRITORY,
                GIVE_MY_CONTACT,
                VOLUNTEER,
                ROLLBACK);

        SendMessage sendMessage = new SendMessage(chatId, "Выберите интересующую информацию").replyMarkup(keyboard);
        telegramBot.execute(sendMessage);
        return sendMessage;

    }


    public SendMessage getInfoAboutTakeAnimal(Long chatId, boolean isCat) {

        if (!isCat) {
            InlineKeyboardMarkup keyboard = keyboardUtil.setKeyboard(
                    SHELTER_RULES_BEFORE_MEETING_ANIMAL,
                    DOCUMENTS_TO_TAKE_ANIMAL,
                    TRANSPORTATION_ADVICE,
                    HOUSE_RULES_FOR_SMALL_ANIMAL,
                    HOUSE_RULES_FOR_ADULT_ANIMAL,
                    HOUSE_RULES_FOR_ANIMAL_WITH_DISABILITY,
                    CYNOLOGIST_ADVICE,
                    CYNOLOGISTS,
                    REFUSE_REASONS,
                    GIVE_MY_CONTACT,
                    ROLLBACK);

            SendMessage sendMessage = new SendMessage(chatId, "Постараюсь дать Вам полную информацию " +
                    "о том, как разобраться с бюрократическими (оформление договора) и бытовыми (подготовка к жизни с животным) " +
                    "вопросами.").replyMarkup(keyboard);
            telegramBot.execute(sendMessage);
            return sendMessage;
        } else {
            InlineKeyboardMarkup keyboard = keyboardUtil.setKeyboard(
                    SHELTER_RULES_BEFORE_MEETING_ANIMAL,
                    DOCUMENTS_TO_TAKE_ANIMAL,
                    TRANSPORTATION_ADVICE,
                    HOUSE_RULES_FOR_SMALL_ANIMAL,
                    HOUSE_RULES_FOR_ADULT_ANIMAL,
                    HOUSE_RULES_FOR_ANIMAL_WITH_DISABILITY,
                    GIVE_MY_CONTACT,
                    ROLLBACK);

            SendMessage sendMessage = new SendMessage(chatId, "Постараюсь дать Вам полную информацию " +
                    "о том, как разобраться с бюрократическими (оформление договора) и бытовыми (подготовка к жизни с животным) " +
                    "вопросами.").replyMarkup(keyboard);
            telegramBot.execute(sendMessage);
            return sendMessage;
        }
    }



}