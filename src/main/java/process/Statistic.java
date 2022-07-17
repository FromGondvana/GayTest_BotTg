package process;

import java.util.ArrayList;
import java.util.List;

public class Statistic {
    private List<String> chatIdList = new ArrayList<>();
    private List<RunningTest> runTests = new ArrayList<>();

    public void addId(String id) {
        if (!chatIdList.contains(id)) {
            chatIdList.add(id);
        }
    }

    public void addRunTest(RunningTest runningTest) {
        if (!runTests.contains(runningTest)) {
            runTests.add(runningTest);
        }
    }
    public void delRunTest(String id) {

        int index = 0;
        for(RunningTest test : runTests)
        {
            if(test.isEqualsId(id))
                break;
            else
                index = index + 1;
        }

        runTests.remove(index);

    }

    public RunningTest getRunTest(String id) {
        for(RunningTest test : runTests)
        {
            if(test.isEqualsId(id))
                return test;
        }
        return null;
    }

    public boolean isStartTest(String id)
    {
        for(RunningTest test : runTests)
        {
            if(test.isEqualsId(id))
                return true;
        }
        return false;
    }


}
