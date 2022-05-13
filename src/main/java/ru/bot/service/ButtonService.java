package ru.bot.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class ButtonService {

    //передаем какие-то кнопки, а с ними уже что-то делают
    public ReplyKeyboardMarkup setButtons(List<KeyboardRow> KeyboardListRow){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);//кнопки под разных клиентов
        replyKeyboardMarkup.setKeyboard(KeyboardListRow);
        return replyKeyboardMarkup;
    }

    //создаем кнопки
    public List<KeyboardRow> createButtons(List<String> buttonsName){
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();
        for (String buttonName : buttonsName){
            keyboardRow.add(new KeyboardButton(buttonName));
        }
        keyboardRows.add(keyboardRow);
        return keyboardRows;
    }
}
