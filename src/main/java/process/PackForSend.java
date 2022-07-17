package process;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

public class PackForSend {
    String id;
    String txt;
    ReplyKeyboard keyboard;

    public PackForSend(String id, String txt, ReplyKeyboard keyboard) {
        this.id = id;
        this.txt = txt;
        this.keyboard = keyboard;
    }

    public String getId() {
        return id;
    }

    public String getTxt() {
        return txt;
    }

    public ReplyKeyboard getKeyboard() {
        return keyboard;
    }
}
