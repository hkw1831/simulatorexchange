package simulator.handler;

import exception.SimulatorException;
import message.DMAMsg;
import simulator.connectivity.ConnectionState;
import simulator.connectivity.MessageSenderService;
import util.SimulatorLogger;

public class LogonMessageHandler extends AbstractMessageHandler
{

    public LogonMessageHandler(ConnectionState connectable, MessageSenderService senderService, SimulatorLogger logger)
    {
        super(connectable, senderService, logger);
    }

    @Override
    public void handle(DMAMsg msg) throws SimulatorException
    {
        logger.traceClientLogon();
        sendMessage(msg);
    }

}
