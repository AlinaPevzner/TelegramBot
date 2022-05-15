package ru.bot.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.List;

import static java.util.Arrays.asList;
import static ru.bot.cons.VarConst.*;

public class SendMessageOperationsService {

    private final String GREETING_MESSAGE = "Привет! Давай запланиуремся?";
    private final String PLANNING_MESSAGE = "Шо сегодня мутишь?";
    private final String END_PLANNING_MESSAGE = "Напланирвоались ууух";

    private final ButtonService buttonService = new ButtonService();

    public SendMessage createGreetingInformation(Update update){
        SendMessage sendMessage = createSimpleMessage(update, GREETING_MESSAGE);
        ReplyKeyboardMarkup replyKeyboardMarkup =
                buttonService.setButtons(buttonService
                        .createButtons(asList(START_PLANNING, END_PLANNING, SHOW_DEALS)));
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return sendMessage;
    }

    public SendMessage createPlanningMessage (Update update){
        return createSimpleMessage(update, PLANNING_MESSAGE);
    }

    public SendMessage createEndPlanningMessage (Update update){
        return createSimpleMessage(update, END_PLANNING_MESSAGE);
    }

    public SendMessage createSimpleMessage(Update update, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
        sendMessage.setText(message);
        return sendMessage;
    }

    public SendMessage createSimpleMessage2(Update update, List<String> messages) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
        StringBuilder message = new StringBuilder();
        for (String s : messages){
            message.append(s).append("\n");
        }
        sendMessage.setText(message.toString());
        return sendMessage;
    }

//    public SendMessage createInstructionMessage(Update update) {
//        SendMessage sendMessage = createSimpleMessage(update);
//    }
}
