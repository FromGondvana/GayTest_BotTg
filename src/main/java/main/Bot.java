package main;

import data.QuestionData;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import process.Act;
import process.KeyboardSetting;

import java.util.Locale;

public class Bot extends TelegramLongPollingBot {
    QuestionData questionData;
    Act act;
    KeyboardSetting keyboardSetting;

    boolean isNameQue = false;
    boolean isFatalAns = false;
    boolean isTotal = false;

    Bot()
    {
        questionData = new QuestionData();
        questionData.initLists();
        act = new Act(questionData.getSizeList());
        keyboardSetting = new KeyboardSetting();
        keyboardSetting.updateKeyboard(true);
    }
    @Override
    public String getBotUsername() {
        return Sys.uname();
    }

    @Override
    public String getBotToken() {
        return Sys.token();
    }

    @Override
    public void onUpdateReceived(Update update) {
        try{
            if(update.hasMessage() && update.getMessage().hasText()) {

                String response = "Не понимаю тебя";
                Message inMsg = update.getMessage();

                String chatId = inMsg.getChatId().toString();
                String textInMsg = inMsg.getText().toLowerCase(Locale.ROOT);

                if(textInMsg.equals("/start"))
                {
                    response = "Привет дружище, готов проверить себя на пидора?";
                }
                else if(!act.isRun() || isTotal)
                {
                    isTotal = false;
                    textInMsg = textInMsg.toLowerCase(Locale.ROOT);
                    if(textInMsg.equals("да"))
                    {
                        response = "Ай шалун!!!\n";
                        response = response.concat(questionData.getQuestion(1));

                        isNameQue = true;
                        act.startAct();
                    }
                    else if(textInMsg.equals("нет"))
                    {
                        response = "ПИДОРА ОТВЕТ\n" +
                                "Подумай хорошенько еще раз";
                    }
                }
                else if(act.isRun() && act.getIndexQue() <= questionData.getSizeList())
                {
                    if(textInMsg.equals("да"))
                    {
                        act.nextStep(true);

                        keyboardSetting.initQueKeyboard();
                        keyboardSetting.updateKeyboard(false);
                    }
                    else if(textInMsg.equals("нет"))
                    {
                        act.nextStep(false);

                        keyboardSetting.initQueKeyboard();
                        keyboardSetting.updateKeyboard(false);
                    }
                    else if(isNameQue)
                    {
                        if(textInMsg.equals("олег") || textInMsg.equals("никита") || textInMsg.equals("саша"))
                        {
                            isFatalAns = true;
                            act.finishAct();
                            isNameQue = false;
                            keyboardSetting.updateKeyboard(true);
                        }
                        else {
                            act.nextStep(false);
                            keyboardSetting.initQueKeyboard();
                            keyboardSetting.updateKeyboard(false);
                            isNameQue = false;
                        }
                    }

                    if(act.isRun() && act.getIndexQue() >= questionData.getSizeList())
                    {
                        String result = Integer.toString(act.getResult());

                        System.out.println(result);
                        response = "Готово. Ты пидор на ".concat(result).concat(" %. Надеюсь твои друзья гордятся тобой\n")
                                .concat("Хочешь еще?");
                        isTotal = true;
                        act.finishAct();

                        keyboardSetting.updateKeyboard(true);
                    }
                    else if(isFatalAns)
                    {
                        isFatalAns = false;
                        isNameQue = false;
                        isTotal = true;

                        response = " ОЙ ОЙ ОЙ. Да вы же пидор на все 100 процентов\n" +
                                "Ну что? Попытаешь свою удачу еще раз?";
                        act.finishAct();
                        keyboardSetting.updateKeyboard(true);

                    }
                    else
                    {
                        response = questionData.getQuestion(act.getIndexQue());
                    }
                }

                SendMessage outMess = new SendMessage();

                if(isNameQue)
                    outMess.setReplyMarkup(keyboardSetting.getReplyKeyboardRemove());
                else
                    outMess.setReplyMarkup(keyboardSetting.getRepKeMarkup());
                outMess.setChatId(chatId);
                outMess.setText(response);

                execute(outMess);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
