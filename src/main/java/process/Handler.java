package process;

import main.UserData;
import main.SystemData;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.Locale;

public class Handler {
    private UserData userData;
    private SystemData sys;
    private GameScene gameScene;
    private KeyboardSetting keySett;
    //private ReplyKeyboard keyboard;

    public Handler() {
        userData = new UserData();
        sys = new SystemData();
        gameScene = new GameScene();
        keySett = new KeyboardSetting();
        keySett.updateKeyboard();
    }

    public PackForSend parse(Message inMsg) {
        PackForSend response;

        String chatId = inMsg.getChatId().toString();
        String textMsg = inMsg.getText();
        String mainMenuLabel = "Приветствую вас. QuizBot по столицам мира. Подтянине себя в географии)";

        if (textMsg.equals("/start")) {
            userData.addId(chatId);
            keySett.updateKeyboard();
            ReplyKeyboard keyboard;
            keyboard = keySett.getRepKeMarkup();

            response = new PackForSend(chatId, mainMenuLabel, keyboard);
        }
        else if (textMsg.equals("Играть")) {

            userData.addPlayerPlay(new PlayGame(chatId));
            //ReplyKeyboard keyboard = gameScene.keyboard();
            response = gameScene.initMessageQuestion(chatId);

        }
        else{
            String responseText;
            if(inMsg.equals(gameScene.buffAnswer))
            {
                responseText = "Это правильный ответ\n";
                userData.getPlPlays(chatId).nextStep(true);
            }
            else
            {
                responseText = "Это неправильный ответ\n";
                userData.getPlPlays(chatId).nextStep(false);
            }
            response = gameScene.initMessageQuestion(chatId);
            response.setTxtQ(responseText.concat(response.getTxtQ()));

            if(userData.getPlPlays(chatId).getIndex() == gameScene.countQuestion);
            {
                response.setTxtQ("Ваш результат ".concat(Integer.toString(userData.getPlPlays(chatId).getResult())));
                response.setKeyboard(keySett.getRepKeMarkup());
            }
        }

        return response;
    }
}
