package process;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;

public class KeyboardSetting {
    private ReplyKeyboardRemove replyKeyboardRemove;
    private ReplyKeyboardMarkup replyKeyboardMarkup;
    private ArrayList<KeyboardRow> keyboardRows;
    private ArrayList<String> keyTextList;
    private KeyboardRow mainKeyRow;
    private KeyboardRow askKeyRow;

    public KeyboardSetting()
    {
        replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardRemove = new ReplyKeyboardRemove();
        keyboardRows = new ArrayList<>();
        keyTextList = new ArrayList<>();
        mainKeyRow = new KeyboardRow();
        askKeyRow = new KeyboardRow();

        keyTextList.add("Да");
        keyTextList.add("Нет");

        initMainKeyboard();
    }

    public void initMainKeyboard()
    {
        mainKeyRow.add(new KeyboardButton("Да"));
        mainKeyRow.add(new KeyboardButton("Нет"));
        mainKeyRow.add(new KeyboardButton("Инфо"));
        mainKeyRow.add(new KeyboardButton("Обратная связь"));
    }

    public void initQueKeyboard(){
        askKeyRow.clear();
        int rand = (int)(Math.random() * 10);
        if(rand <= 5)
        {
            askKeyRow.add(new KeyboardButton("Да"));
            askKeyRow.add(new KeyboardButton("Нет"));
        }
        else
        {
            askKeyRow.add(new KeyboardButton("Нет"));
            askKeyRow.add(new KeyboardButton("Да"));
        }
    }

    public void updateKeyboard(boolean isStartKey)
    {
        keyboardRows.clear();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        if(isStartKey)
            keyboardRows.add(mainKeyRow);
        else
            keyboardRows.add(askKeyRow);

        replyKeyboardMarkup.setKeyboard(keyboardRows);
        //keyboard = replyKeyboardMarkup;
    }

    public ReplyKeyboardMarkup getRepKeMarkup() {
        return replyKeyboardMarkup;
    }

    public ReplyKeyboardRemove getReplyKeyboardRemove() {
        return replyKeyboardRemove;
    }

    public void initKeyboardRemove() {
        replyKeyboardRemove.setRemoveKeyboard(true);
        replyKeyboardRemove.setSelective(true);
    }

    /*public ReplyKeyboard getKeyboard() {
        return keyboard;
    }*/
}
