package process;

import main.SystemData;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.util.Locale;

public class Handler {
    private Statistic stat;
    private SystemData sys;
    private KeyboardSetting keySett;
    ReplyKeyboard keyboard;

    public Handler()
    {
        stat = new Statistic();
        sys = new SystemData();
        keySett = new KeyboardSetting();
        keySett.updateKeyboard(true);
        sys.initLists();
        //!!!!!!!!!
    }
    public PackForSend parse(Message inMsg)
    {
        String response = "hANDLER";
        boolean isStart = false;

        String chatId = inMsg.getChatId().toString();
        String textMsg = inMsg.getText().toLowerCase(Locale.ROOT);


        if(stat.isStartTest(chatId))
            isStart = true;

        if(!isStart)
        {
            if(textMsg.equals("/start"))
            {
                response = "Привет дружище, я подготовил для тебя особый тест, готов?";

                stat.addId(chatId);
                keySett.updateKeyboard(true);
                keyboard = keySett.getRepKeMarkup();
            }
            else if(textMsg.equals("да"))
            {
                response = "Проверим твою слабость на задок!!!\n";
                response = response.concat(sys.getQuestion(1));

                stat.addRunTest(new RunningTest(chatId));
                keySett.initKeyboardRemove();
                keyboard = keySett.getReplyKeyboardRemove();
            }
            else if(textMsg.equals("нет"))
            {
                response = "ПИДОРА ОТВЕТ\n" +
                        "Подумай хорошенько еще раз";
            }
        }
        else if(isStart && (stat.getRunTest(chatId).getIndex() == 1))
        {
            if(textMsg.equals("олег") || textMsg.equals("никита") || textMsg.equals("саша"))
            {
                response = getFinalResponse(false, chatId);

                keySett.updateKeyboard(true);
                keyboard = keySett.getRepKeMarkup();
            }
            else
            {
                stat.getRunTest(chatId).nextStep(false);

                keySett.initQueKeyboard();
                keySett.updateKeyboard(false);
                keyboard = keySett.getRepKeMarkup();
                response = sys.getQuestion(stat.getRunTest(chatId).getIndex());
            }
        }
        else if(isStart && (stat.getRunTest(chatId).getIndex() <= sys.getSizeQList()))
        {
            System.out.println(stat.getRunTest(chatId).getIndex());
            System.out.println(sys.getSizeQList() + "\n");
            if(textMsg.equals("да"))
            {
                stat.getRunTest(chatId).nextStep(true);
            }
            else if(textMsg.equals("нет"))
            {
                stat.getRunTest(chatId).nextStep(false);

            }


            if(stat.getRunTest(chatId).getIndex() <= sys.getSizeQList())
            {
                keySett.initQueKeyboard();
                keySett.updateKeyboard(false);
                keyboard = keySett.getRepKeMarkup();
                response = sys.getQuestion(stat.getRunTest(chatId).getIndex());
            }

            else if(stat.getRunTest(chatId).getIndex() > sys.getSizeQList())
            {
                response = getFinalResponse(true, chatId);
                stat.delRunTest(chatId);
                System.out.println(response);

                keySett.updateKeyboard(true);
                keyboard = keySett.getRepKeMarkup();
            }
        }


        PackForSend pack = new PackForSend(chatId, response, keyboard);

        return pack;
    }

    String getFinalResponse(boolean isPassTheFull, String id)
    {
        String response;
        if(isPassTheFull) {
            System.out.println(stat.getRunTest(id).getResult());
            String result = Integer.toString(stat.getRunTest(id).getResult());

            response = result.concat("% - твой результат. Надеюсь твои друзья гордятся тобой\n" +
                    "Хочешь еще?");
        }
        else
        {
            stat.delRunTest(id);

            response = " ОЙ ОЙ ОЙ. Да вы же пидор на все 100 процентов\n" +
                    "Ну что? Попытаешь свою удачу еще раз?";

        }

        return response;
    }
}
