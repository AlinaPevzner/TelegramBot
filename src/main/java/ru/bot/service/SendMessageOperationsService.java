package ru.bot.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class SendMessageOperationsService {

    private final String greetingMessage = "Hello, friend";

    public SendMessage createGreetingInformation(Update update){
        return createSimpleMessage(update, greetingMessage);
    }

    private SendMessage createSimpleMessage(Update update, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
        sendMessage.setText(message);
        return sendMessage;
    }
}
