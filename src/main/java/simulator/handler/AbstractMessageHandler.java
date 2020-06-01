package simulator.handler;

import exception.MessageCodecException;
import exception.SimulatorException;
import message.DMAMsg;
import simulator.connectivity.ConnectionState;
import simulator.connectivity.MessageSenderService;
import util.SimulatorLogger;

import java.io.IOException;

public abstract class AbstractMessageHandler implements MessageHandler
{

    protected ConnectionState connectable;
    protected MessageSenderService senderService;
    protected SimulatorLogger logger;

    public AbstractMessageHandler(ConnectionState connectable, MessageSenderService senderService, SimulatorLogger logger)
    {
        super();
        this.connectable = connectable;
        this.senderService = senderService;
        this.logger = logger;
    }

    protected void sendMessage(DMAMsg executionReport) throws SimulatorException
    {
        try
        {
            senderService.sendMessage(executionReport);
        }
        catch (IOException | MessageCodecException e)
        {
            throw new SimulatorException(e);
        }
    }

    protected void tryToMatchOrder(DMAMsg executionReport) throws SimulatorException
    {
        MessageHandler tradeHandler = new TradeMessageHandler(connectable, senderService, logger);
        tradeHandler.handle(executionReport);
    }

}
