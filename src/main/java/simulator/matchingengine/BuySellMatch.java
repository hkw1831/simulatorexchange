package simulator.matchingengine;

import message.DMAMsgSide;

public class BuySellMatch
{
    public static String oppositeSide(String side)
    {
        return DMAMsgSide.BUY.equals(side) ? DMAMsgSide.SELL : DMAMsgSide.BUY;
    }
}
