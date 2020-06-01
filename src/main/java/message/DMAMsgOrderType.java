package message;

import java.util.*;

public class DMAMsgOrderType
{
    public static String LIMIT = "limit";
    public static String MARKET = "market";

    private static final Set<String> values = new HashSet<>(Arrays.asList(LIMIT, MARKET));

    public static boolean checkValidValue(String exchangeValue)
    {
        return values.contains(exchangeValue);
    }
}
