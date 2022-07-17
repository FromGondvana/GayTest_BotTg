package process;

public class Act {
    private int indexQue;
    private int countAnswerYes;
    private boolean isRun;
    private int countQue;

    public Act(int countQue)
    {
        indexQue = 0;
        countAnswerYes = 0;
        isRun = false;
        this.countQue = countQue;
    }

    public void startAct()
    {
        isRun = true;
        indexQue = 1;
    }

    public void finishAct()
    {
        isRun = false;
        indexQue = 0;
        countAnswerYes = 0;
    }

    public void nextStep(boolean answer)
    {
        indexQue = indexQue + 1;
        if(answer) {
            countAnswerYes = countAnswerYes + 1;
        }
    }

    public int getIndexQue() {
        return indexQue;
    }

    public boolean isRun() {
        return isRun;
    }

    public int getResult()
    {
        double procent = ((double) countAnswerYes) / ((double) countQue - 1) * 100;
        int res = (int) procent;
        return res;
    }
}
