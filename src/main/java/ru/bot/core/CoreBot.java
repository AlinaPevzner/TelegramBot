package ru.bot.core;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.bot.service.SendMessageOperationsService;

import static ru.bot.cons.VarConst.START;

public class CoreBot extends TelegramLongPollingBot {


    @Override
    public void onUpdateReceived(Update update) {
        SendMessageOperationsService sendMessageOperationsService = new SendMessageOperationsService();
        if (update.hasMessage() && update.getMessage().hasText()){
            switch (update.getMessage().getText()){
                case START:
                    try {
                        execute(sendMessageOperationsService.createGreetingInformation(update));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "tracker_f_life_bot";
    }

    @Override
    public String getBotToken() {
        return "5265268112:AAHvLJdV2hjScAf-BhjOqlMMtgNH2ThgJms";
    }

}
