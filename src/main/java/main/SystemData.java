package main;

import java.util.ArrayList;
import java.util.List;

public class SystemData {
    private static final String BOT_NAME = "Gay_test_bot";
    private static final String BOT_USERNAME = "Test_for_gay_bot";
    private static final String BOT_TOKEN = "5160136736:AAHh92YSFV4NcQZwX3Yr5nFlUdCRuZNp6NY";

    private List<String> questions = new ArrayList<>();

    public void initLists()
    {
        questions.add("Начнем с простого, как тебя зовут?");
        questions.add("Считаешь ли ты себя красавчиком?");
        questions.add("Твой друг переодевает футболку, приятные ли ощущения ты испытываешь при этом?");
        questions.add("В автобусе незнакомый мужчина подмигивает тебе, подмигнешь ли ты в ответ?");
        questions.add("В твоем плейлисте есть Егор Крид?");
        questions.add("В школьное время ты ходил в туалет для девочек?");
        questions.add("Ты писаешь сидя?");
        questions.add("Стульчак в твоей квартире всегда опущен?");

    }

    public String getQuestion(int index)
    {
        String response = Integer.toString(index).concat(")").concat(questions.get(index - 1));

        return response;
    }

    public int getSizeQList()
    {
        return questions.size();
    }

    static String name()
    {
        return BOT_NAME;
    }

    static String uname()
    {
        return BOT_USERNAME;
    }

    static String token()
    {
        return BOT_TOKEN;
    }
}
