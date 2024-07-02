package ru.bot.core;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.bot.service.SendMessageOperationsService;
import ru.bot.store.HashMapStore;

import java.time.LocalDate;

import static ru.bot.cons.VarConst.*;

public class CoreBot extends TelegramLongPollingBot {
    private SendMessageOperationsService sendMessageOperationsService = new SendMessageOperationsService();
    private HashMapStore store = new HashMapStore();
    private boolean startPlanning;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()){
            switch (update.getMessage().getText()){
                case START:
                    executeMessage(sendMessageOperationsService.createGreetingInformation(update));
                    //executeMessage(sendMessageOperationsService.createInstructionMessage(update));
                    break;
                case START_PLANNING:
                    startPlanning = true;
                    executeMessage(sendMessageOperationsService.createPlanningMessage(update));
                    break;
                case END_PLANNING:
                    startPlanning = false;
                    executeMessage(sendMessageOperationsService.createEndPlanningMessage(update));
                    break;
                case SHOW_DEALS:
                    if (startPlanning == false) {
                        executeMessage(sendMessageOperationsService.createSimpleMessage2(update, store.selectAll(LocalDate.now())));
                    }
                //записать все хеш мапу fsdsfd
                default:
                    if (startPlanning == true) {
                        store.save(LocalDate.now(), update.getMessage().getText());
                    }
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "obormoot_bot";
    }

    @Override
    public String getBotToken() {
        return "5306159225:AAHJEBNfP6edSu_jgz46NwLSmZ42qN33dQA";
    }

    private <T extends BotApiMethod> void executeMessage(T sendMessage){
        try{
            execute(sendMessage);
        } catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

}
