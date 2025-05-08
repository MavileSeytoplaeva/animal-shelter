package com.shelter.animalshelter.util;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KeyboardUtil {

    public InlineKeyboardMarkup setKeyboard(CallbackDataRequest... strings) { //В значениях strings только те константы, которые есть в enum
        List<InlineKeyboardButton> keyboardButtons = getKeyboardButtons(strings);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        for (InlineKeyboardButton keyboardButton : keyboardButtons) {
            inlineKeyboardMarkup.addRow(keyboardButton);
        }
        return inlineKeyboardMarkup;
    }

    private List<InlineKeyboardButton> getKeyboardButtons(CallbackDataRequest... strings) {
        List<InlineKeyboardButton> inlineKeyboardButtons = new ArrayList<>();
        for (CallbackDataRequest string : strings) {
            inlineKeyboardButtons.add(button(string.getText(), string.getCallbackData()));
        }
        return inlineKeyboardButtons;
    }

    private InlineKeyboardButton button(String text, String callbackData) {
        InlineKeyboardButton button = new InlineKeyboardButton(text);
        button.callbackData(callbackData);
        return button;
    }
}