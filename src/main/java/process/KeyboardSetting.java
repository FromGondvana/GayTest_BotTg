package process;

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
    private KeyboardRow firstKeyRow;

    public KeyboardSetting()
    {
        replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardRemove = new ReplyKeyboardRemove();
        keyboardRows = new ArrayList<>();
        keyTextList = new ArrayList<>();
        firstKeyRow = new KeyboardRow();

        initMainKeyboard();
    }

    public void initMainKeyboard()
    {
        firstKeyRow.add(new KeyboardButton("Играть"));
        //mainKeyRow.add(new KeyboardButton(""));
        firstKeyRow.add(new KeyboardButton("Инфо"));
        firstKeyRow.add(new KeyboardButton("Обратная связь"));
    }

    public void updateKeyboard()
    {
        keyboardRows.clear();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        keyboardRows.add(firstKeyRow);

        replyKeyboardMarkup.setKeyboard(keyboardRows);
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
}
