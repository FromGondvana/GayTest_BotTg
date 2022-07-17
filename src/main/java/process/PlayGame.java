package process;

import java.util.Objects;

public class PlayGame {
    private int index;
    private int countYesAnsw;
    private String id;

    public PlayGame(String id) {
        this.id = id;
        this.index = 1;
        this.countYesAnsw = 0;
    }

    public void nextStep(boolean answer)
    {
        index = index + 1;
        if(answer) {
            countYesAnsw = countYesAnsw + 1;
        }
    }

    public boolean isEqualsId(String id)
    {
        if(id.equals(this.id))
            return true;
        else
            return false;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayGame that = (PlayGame) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public int getIndex() {
        return index;
    }
    public int getResult()
    {
        double procent = ((double) countYesAnsw) / ((double) index) * 100;
        int res = (int) procent;
        return res;
    }
}
