package simulator.matchingengine;

import message.DMAMsg;
import message.DMAMsgBodyExecutionReport;
import message.DMAMsgSide;

import java.util.*;

public class DMAMsgDatabase
{

    private static Map<String, DMAMsg> ordIdToMsg = new LinkedHashMap<>();
    private static Map<String, String> clOrdIdToOrdId = new LinkedHashMap<>();

    public static void putOrdIdToMsg(String ordId, DMAMsg msg)
    {
        ordIdToMsg.put(ordId, msg);
    }

    public static void removeOrdIdToMsg(String ordId)
    {
        ordIdToMsg.remove(ordId);
    }

    public static DMAMsg getMsgFromOrderId(String ordId)
    {
        return ordIdToMsg.get(ordId);
    }

    public static List<DMAMsg> findMatchLimitOrder(String side, Integer price, String instrumentId)
    {
        List<DMAMsg> list = new ArrayList<>();
        for (Map.Entry<String, DMAMsg> entry : ordIdToMsg.entrySet())
        {
            DMAMsg outstandingMsg = entry.getValue();
            DMAMsgBodyExecutionReport outstandingExecReport = (DMAMsgBodyExecutionReport) (outstandingMsg.getBody());
            String outstandingSide = BuySellMatch.oppositeSide(outstandingExecReport.getSide());
            Integer outstandingPrice = outstandingExecReport.getPrice();
            String outstandingInstrumentId = outstandingExecReport.getInstrumentId();

            if (instrumentId.equals(outstandingInstrumentId) && side.equals(outstandingSide) && DMAMsgSide.BUY.equals(side) && price.compareTo(outstandingPrice) >= 0)
            {
                list.add(outstandingMsg);
            }
            else if (instrumentId.equals(outstandingInstrumentId) && side.equals(outstandingSide) && DMAMsgSide.SELL.equals(side) && price.compareTo(outstandingPrice) <= 0)
            {
                list.add(outstandingMsg);
            }
        }
        sortMatchedOrder(side, list);
        return list;
    }

    public static List<DMAMsg> findMatchMarketOrder(String side, String instrumentId)
    {
        List<DMAMsg> list = new ArrayList<>();
        for (Map.Entry<String, DMAMsg> entry : ordIdToMsg.entrySet())
        {
            DMAMsg outstandingMsg = entry.getValue();
            DMAMsgBodyExecutionReport outstandingExecReport = (DMAMsgBodyExecutionReport) (outstandingMsg.getBody());
            String outstandingSide = BuySellMatch.oppositeSide(outstandingExecReport.getSide());
            String outstandingInstrumentId = outstandingExecReport.getInstrumentId();

            if (side.equals(outstandingSide) && instrumentId.equals(outstandingInstrumentId))
            {
                list.add(outstandingMsg);
            }
        }
        sortMatchedOrder(side, list);
        return list;
    }

    protected static void sortMatchedOrder(String side, List<DMAMsg> list)
    {
        if (DMAMsgSide.BUY.equals(side))
        {
            Comparator<DMAMsg> ascendingPrice = (DMAMsg a, DMAMsg b) -> {
                DMAMsgBodyExecutionReport aExecReport = (DMAMsgBodyExecutionReport) (a.getBody());
                DMAMsgBodyExecutionReport bExecReport = (DMAMsgBodyExecutionReport) (b.getBody());
                return aExecReport.getPrice().compareTo(bExecReport.getPrice());
            };
            Collections.sort(list, ascendingPrice);
        }
        else
        {
            Comparator<DMAMsg> decendingPrice = (DMAMsg a, DMAMsg b) -> {
                DMAMsgBodyExecutionReport aExecReport = (DMAMsgBodyExecutionReport) (a.getBody());
                DMAMsgBodyExecutionReport bExecReport = (DMAMsgBodyExecutionReport) (b.getBody());
                return bExecReport.getPrice().compareTo(aExecReport.getPrice());
            };
            Collections.sort(list, decendingPrice);
        }
    }

    public static void putClordIdToOrdId(String clOrdId, String ordId)
    {
        clOrdIdToOrdId.put(clOrdId, ordId);
    }

    public static void removeClordIdToOrdId(String clOrdId)
    {
        clOrdIdToOrdId.remove(clOrdId);
    }

    public static String getOrdIdFromClOrdId(String clOrdId)
    {
        return clOrdIdToOrdId.get(clOrdId);
    }

    public static void clearAll()
    {
        ordIdToMsg = new LinkedHashMap<>();
        clOrdIdToOrdId = new LinkedHashMap<>();

    }

}
