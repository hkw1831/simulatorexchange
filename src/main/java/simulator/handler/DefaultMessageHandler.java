package simulator.handler;

import exception.SimulatorException;
import message.DMAMsg;
import simulator.connectivity.ConnectionState;
import simulator.connectivity.MessageSenderService;
import util.SimulatorLogger;
import simulator.messagegenerator.SimulatorDMAMsgGenerator;
import simulator.messagegenerator.SimulatorDMAMsgInvalidMessageTypeReportGenerator;

public class DefaultMessageHandler extends AbstractMessageHandler
{

    public DefaultMessageHandler(ConnectionState connectable, MessageSenderService senderService, SimulatorLogger logger)
    {
        super(connectable, senderService, logger);
    }

    @Override
    public void handle(DMAMsg msg) throws SimulatorException
    {
        SimulatorDMAMsgGenerator generator = new SimulatorDMAMsgInvalidMessageTypeReportGenerator(msg);
        DMAMsg executionReport = generator.createExecutionReoprt();
        sendMessage(executionReport);
    }

}
