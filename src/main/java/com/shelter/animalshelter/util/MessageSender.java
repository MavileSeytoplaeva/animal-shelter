package com.shelter.animalshelter.util;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;

@Service
public class MessageSender {

    private final TelegramBot telegramBot;

    public MessageSender(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    public SendMessage sendMessage(Long chatId, String message) {
        SendMessage sendMessage = new SendMessage(chatId, message);
        telegramBot.execute(sendMessage);
        return sendMessage;
    }
}
