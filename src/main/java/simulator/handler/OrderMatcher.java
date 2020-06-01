package simulator.handler;

import message.*;
import simulator.matchingengine.DMAMsgDatabase;
import simulator.matchingengine.MatchedTrade;

import java.util.List;

public class OrderMatcher
{
    private DMAMsg inputOrder;
    private DMAMsgBodyExecutionReport inputExecReport;
    private String inputSide;
    private Integer inputPrice;
    private String inputInstrumentId;
    private Integer inputQuantity;
    private Integer filledQuantity;
    private MatchedTrade matchedOrders;
    private String orderType;
    private String timeInForce;

    public OrderMatcher(DMAMsg inputOrder)
    {
        this.inputOrder = inputOrder;
        inputExecReport = (DMAMsgBodyExecutionReport) (inputOrder.getBody());
        inputSide = inputExecReport.getSide();
        inputPrice = inputExecReport.getPrice();
        inputInstrumentId = inputExecReport.getInstrumentId();
        inputQuantity = inputExecReport.getOrderQuantity();
        orderType = inputExecReport.getOrderType();
        filledQuantity = 0;
        timeInForce = inputExecReport.getTimeInForce();
    }

    public MatchedTrade createMatchedTrade()
    {
        matchedOrders = new MatchedTrade();
        List<DMAMsg> potentialMatchedOrders = isLimitOrder() ? DMAMsgDatabase.findMatchLimitOrder(inputSide, inputPrice, inputInstrumentId) : DMAMsgDatabase.findMatchMarketOrder(inputSide, inputInstrumentId);

        int unmatchQuantityInOrderBook = 0;
        for (DMAMsg potentialMatchedOrder : potentialMatchedOrders)
        {
            DMAMsgBody body = potentialMatchedOrder.getBody();
            unmatchQuantityInOrderBook = unmatchQuantityInOrderBook + ((DMAMsgBodyExecutionReport)body).getLeavesQuantity();
        }
        if (timeInForce.equals(DMAMsgTimeInForce.FOK) && inputQuantity > unmatchQuantityInOrderBook) // FOK no match
        {
            return matchedOrders;
        }

        for (DMAMsg potentialMatchedOrder : potentialMatchedOrders)
        {
            if (alreadyMatchedAllQuantity())
            {
                break;
            }

            DMAMsgBodyExecutionReport matchedExecReport = (DMAMsgBodyExecutionReport) (potentialMatchedOrder.getBody());
            Integer lastQuantity = isPartFilled(matchedExecReport) ? (inputQuantity - filledQuantity) : (matchedExecReport.getLeavesQuantity());
            Integer lastPrice = matchedExecReport.getPrice();

            addMatchedOrder(potentialMatchedOrder, matchedExecReport, lastQuantity, lastPrice);
            addInputOrder(lastQuantity, lastPrice);
            updateFilledQuantity(matchedExecReport);
        }

        if (isLimitOrder() && inputOrderIsMatched())
        {
            matchedOrders.addToStoredTrade(inputOrder);
        }
        return matchedOrders;
    }

    protected boolean isLimitOrder()
    {
        return DMAMsgOrderType.LIMIT.equals(orderType);
    }

    protected void addInputOrder(Integer lastQuantity, Integer lastPrice)
    {
        DMAMsg updatedInputOrder = updateInputOrder(lastQuantity, lastPrice);
        matchedOrders.addToSentTrade(updatedInputOrder);
    }

    protected void addMatchedOrder(DMAMsg potentialMatchedOrder, DMAMsgBodyExecutionReport matchedExecReport, Integer lastQuantity, Integer lastPrice)
    {
        updateMatchedOrder(matchedExecReport, lastQuantity, lastPrice);
        matchedOrders.addToStoredTrade(potentialMatchedOrder);
        matchedOrders.addToSentTrade(potentialMatchedOrder);
    }

    protected void updateFilledQuantity(DMAMsgBodyExecutionReport matchedExecReport)
    {
        filledQuantity = filledQuantity + matchedExecReport.getLastQuantity();
    }

    protected void updateMatchedOrder(DMAMsgBodyExecutionReport matchedExecReport, Integer lastQuantity, Integer lastPrice)
    {
        matchedExecReport.setLastQuantity(lastQuantity);
        matchedExecReport.setLastPrice(lastPrice);
        matchedExecReport.setLeavesQuantity(matchedExecReport.getLeavesQuantity() - lastQuantity);
        matchedExecReport.setCumulativeQuantity(matchedExecReport.getCumulativeQuantity() + lastQuantity);
        matchedExecReport.setExecType(DMAMsgExecType.TRADE);
    }

    protected boolean isPartFilled(DMAMsgBodyExecutionReport matchedExecReport)
    {
        Integer leavesQuantity = matchedExecReport.getLeavesQuantity();
        int remainingQuantity = inputQuantity - filledQuantity;
        return remainingQuantity < leavesQuantity;
    }

    protected boolean alreadyMatchedAllQuantity()
    {
        return filledQuantity.equals(inputQuantity);
    }

    protected boolean inputOrderIsMatched()
    {
        return !filledQuantity.equals(0);
    }

    protected DMAMsg updateInputOrder(Integer lastQuantity, Integer lastPrice)
    {
        inputExecReport.setLastPrice(lastPrice); // inputPrice
        inputExecReport.setLastQuantity(lastQuantity);
        inputExecReport.setCumulativeQuantity(inputExecReport.getCumulativeQuantity() + lastQuantity);
        inputExecReport.setLeavesQuantity(inputExecReport.getLeavesQuantity() - lastQuantity);
        inputExecReport.setExecType(DMAMsgExecType.TRADE);
        return (DMAMsg) (inputOrder.clone());
    }
}

