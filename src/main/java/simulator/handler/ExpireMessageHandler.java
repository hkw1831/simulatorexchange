package simulator.handler;

import exception.SimulatorException;
import message.DMAMsg;
import message.DMAMsgBodyExecutionReport;
import message.DMAMsgExecType;
import simulator.connectivity.ConnectionState;
import simulator.matchingengine.DMAMsgDatabase;
import simulator.connectivity.MessageSenderService;
import util.SimulatorLogger;
import simulator.messagegenerator.SimulatorDMAMsgExpireExecutionReportGenerator;
import simulator.messagegenerator.SimulatorDMAMsgGenerator;

public class ExpireMessageHandler extends AbstractMessageHandler
{

    public ExpireMessageHandler(ConnectionState connectable, MessageSenderService senderService, SimulatorLogger logger)
    {
        super(connectable, senderService, logger);
    }

    @Override
    public void handle(DMAMsg msg) throws SimulatorException
    {
        SimulatorDMAMsgGenerator generator = new SimulatorDMAMsgExpireExecutionReportGenerator(msg);
        DMAMsg executionReport = generator.createExecutionReoprt();
        storeMessage(executionReport);
        sendMessage(executionReport);
    }

    protected void storeMessage(DMAMsg msg)
    {
        DMAMsgBodyExecutionReport execReport = (DMAMsgBodyExecutionReport) (msg.getBody());
        String execType = execReport.getExecType();
        if (DMAMsgExecType.CANCEL.equals(execType))
        {
            String clOrdId = execReport.getClientOrderId();
            String ordId = execReport.getOrderId();
            logger.traceDeleteMessage(ordId, msg);
            logger.traceDeleteMessageId(clOrdId, ordId);
            DMAMsgDatabase.removeOrdIdToMsg(ordId);
            DMAMsgDatabase.removeClordIdToOrdId(clOrdId);

        }
    }

}
