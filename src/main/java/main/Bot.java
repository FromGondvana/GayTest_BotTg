package main;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import process.Handler;
import process.PackForSend;

public class Bot extends TelegramLongPollingBot {
    private Handler handler;

    Bot()
    {
        handler = new Handler();
    }
    @Override
    public String getBotUsername() {
        return SystemData.uname();
    }

    @Override
    public String getBotToken() {
        return SystemData.token();
    }

    @Override
    public void onUpdateReceived(Update update) {
        try{
            if(update.hasMessage() && update.getMessage().hasText()) {
                SendMessage sendMsg = new SendMessage();
                PackForSend pack = handler.parse(update.getMessage());

                sendMsg.setChatId(update.getMessage().getChatId().toString());;
                sendMsg.setText(pack.getTxtQ());
                sendMsg.setReplyMarkup(pack.getKeyboard());

                /*DeleteMessage outMessage = new DeleteMessage();

                int messId = update.getMessage().getMessageId();
                outMessage.setMessageId(messId);
                outMessage.setChatId(update.getMessage().getChatId().toString());

                execute(outMessage);

                DeleteMessage outMessage1 = new DeleteMessage();


                outMessage1.setMessageId(messId - 1);
                outMessage1.setChatId(update.getMessage().getChatId().toString());

                execute(outMessage1);*/
                execute(sendMsg);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
