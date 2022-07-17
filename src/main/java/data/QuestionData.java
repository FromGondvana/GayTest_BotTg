package data;

import java.util.ArrayList;
import java.util.List;

public class QuestionData {
    private List<String> questionList = new ArrayList<>();
    private int countQue;

    public void initLists()
    {
        questionList.add("Начнем с простого, как тебя зовут?");
        questionList.add("Считаешь ли ты себя красавчиком?");
        questionList.add("Твой друг переодевает футболку, приятные ли ощущения ты испытываешь при этом?");
        questionList.add("В автобусе незнакомый мужчина подмигивает тебе, подмигнешь ли ты в ответ?");
        questionList.add("В твоем плейлисте есть Егор Крид?");
        questionList.add("В школьное время ты ходил в туалет для девочек?");
        questionList.add("Ты писаешь сидя?");
        questionList.add("Стульчак в твоей квартире всегда опущен?");

        countQue = questionList.size();
    }
    public String getQuestion(int index)
    {
        String response = Integer.toString(index).concat(")").concat(questionList.get(index - 1));

        return response;
    }

    public int getSizeList()
    {
        return countQue;
    }
}
