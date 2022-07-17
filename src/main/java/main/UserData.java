package main;

import process.PlayGame;

import java.util.ArrayList;
import java.util.List;

public class UserData {
    private List<String> chatIdList = new ArrayList<>();
    private List<PlayGame> playersPlays = new ArrayList<>();

    public void addId(String id) {
        if (!chatIdList.contains(id)) {
            chatIdList.add(id);
        }
    }

    public void addPlayerPlay(PlayGame playGame) {
        if (!playersPlays.contains(playGame)) {
            playersPlays.add(playGame);
        }
    }
    public void delplayerPlay(String id) {

        int index = 0;
        for(PlayGame test : playersPlays)
        {
            if(test.isEqualsId(id))
                break;
            else
                index = index + 1;
        }

        playersPlays.remove(index);

    }

    public PlayGame getPlPlays(String id) {
        for(PlayGame test : playersPlays)
        {
            if(test.isEqualsId(id))
                return test;
        }
        return null;
    }

    public boolean isPlPlay(String id)
    {
        for(PlayGame test : playersPlays)
        {
            if(test.isEqualsId(id))
                return true;
        }
        return false;
    }


}
