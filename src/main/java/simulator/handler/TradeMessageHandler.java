package simulator.handler;

import exception.SimulatorException;
import message.DMAMsg;
import message.DMAMsgBodyExecutionReport;
import message.DMAMsgTimeInForce;
import simulator.connectivity.ConnectionState;
import simulator.connectivity.MessageSenderService;
import simulator.matchingengine.DMAMsgDatabase;
import simulator.matchingengine.MatchedTrade;
import util.SimulatorLogger;

public class TradeMessageHandler extends AbstractMessageHandler
{

    public TradeMessageHandler(ConnectionState connectable, MessageSenderService senderService, SimulatorLogger logger)
    {
        super(connectable, senderService, logger);
    }

    @Override
    public void handle(DMAMsg msg) throws SimulatorException
    {
        OrderMatcher orderMatcher = new OrderMatcher(msg);
        MatchedTrade tradeExecReport = orderMatcher.createMatchedTrade();
        for (DMAMsg matchedTrade : tradeExecReport.getStoredTrade())
        {
            storeMessage(matchedTrade);
        }
        for (DMAMsg matchedTrade : tradeExecReport.getSentTrade())
        {
            sendMessage(matchedTrade);
        }
        DMAMsgBodyExecutionReport execReport = (DMAMsgBodyExecutionReport) (msg.getBody());
        if (execReport.getTimeInForce().equals(DMAMsgTimeInForce.FAK) || execReport.getTimeInForce().equals(DMAMsgTimeInForce.FOK))
        {
            new ExpireMessageHandler(connectable, senderService, logger).handle(msg);
        }
    }

    protected void storeMessage(DMAMsg msg)
    {
        DMAMsgBodyExecutionReport execReport = (DMAMsgBodyExecutionReport) (msg.getBody());
        if (execReport.getLeavesQuantity() != 0)
        {
            String clOrdId = execReport.getClientOrderId();
            String ordId = execReport.getOrderId();
            logger.traceStoreMessage(ordId, msg);
            logger.traceStoreMessageId(clOrdId, ordId);
            DMAMsgDatabase.putOrdIdToMsg(ordId, msg);
            DMAMsgDatabase.putClordIdToOrdId(clOrdId, ordId);
        }
    }

}
