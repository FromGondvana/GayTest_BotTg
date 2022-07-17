package process;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.ArrayList;
import java.util.List;

public class GameScene {

    GameKeyBoardSettings gameKeySet;
    GameData gameData;
    boolean isEnabledGame;
    String buffAnswer;
    int countQuestion;

    GameScene()
    {
        gameKeySet = new GameKeyBoardSettings();
        isEnabledGame = false;
        gameData = new GameData();
        gameData.initLists();
        countQuestion = gameData.getSizeQList();
    }

    public PackForSend initMessageQuestion(String id)
    {
        PackForSend response;

        isEnabledGame = true;

        List<String> choises = new ArrayList<>();
        List<Integer> choisesInd = new ArrayList<>();
        String correctAns;
        String question;
        int size = gameData.getSizeQList() - 1;
        int choiseBuff = (int) (Math.random() * size);
        choisesInd.add(choiseBuff);
        choises.add(gameData.getQuestion(choiseBuff).getAnswer());
        correctAns = gameData.getQuestion(choiseBuff).getAnswer();
        question = gameData.getQuestion(choiseBuff).getQuestion();

        for(int i = 0; i < 3; i++)
        {
            do {
                choiseBuff = (int) (Math.random() * size);
            }while (choisesInd.contains(choiseBuff));

            choises.add(gameData.getQuestion(choiseBuff).getAnswer());
            choisesInd.add(choiseBuff);
        }


        System.out.println(choises);
        gameKeySet.initKeyboardRows(choises);
        gameKeySet.updateKeyboard();
        ReplyKeyboardMarkup keyboard = gameKeySet.getKeyboardMarkup();

        response = new PackForSend(id, question, correctAns, keyboard);

        System.out.println("232" + response.getId());

        buffAnswer = correctAns;

        return response;
    }
    /*public ReplyKeyboard keyboard()
    {
        return gameKeySet.getKeyboardMarkup();
    }*/
}
