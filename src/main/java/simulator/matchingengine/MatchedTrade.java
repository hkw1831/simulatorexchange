package simulator.matchingengine;

import message.DMAMsg;

import java.util.ArrayList;
import java.util.List;

public class MatchedTrade
{
    private List<DMAMsg> storedTrade;

    private List<DMAMsg> sentTrade;

    public MatchedTrade()
    {
        storedTrade = new ArrayList<>();
        sentTrade = new ArrayList<>();
    }

    public void addToStoredTrade(DMAMsg msg)
    {
        storedTrade.add(msg);
    }

    public void addToSentTrade(DMAMsg msg)
    {
        sentTrade.add(msg);
    }

    public List<DMAMsg> getStoredTrade()
    {
        return storedTrade;
    }

    public List<DMAMsg> getSentTrade()
    {
        return sentTrade;
    }
}
