package message;

import java.util.*;

public class DMAMsgTimeInForce
{
    public static String DAY = "day";
    public static String FAK = "fak";
    public static String FOK = "fok";

    private static final Set<String> values = new HashSet<>(Arrays.asList(DAY, FAK, FOK));

    public static boolean checkValidValue(String exchangeValue)
    {
        return values.contains(exchangeValue);
    }
}
