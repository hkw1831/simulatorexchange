package message;

import java.util.*;

public class DMAMsgSide
{
    public static String BUY = "buy";
    public static String SELL = "sell";

    private static final Set<String> values = new HashSet<>(Arrays.asList(BUY, SELL));

    public static boolean checkValidValue(String exchangeValue)
    {
        return values.contains(exchangeValue);
    }
}
