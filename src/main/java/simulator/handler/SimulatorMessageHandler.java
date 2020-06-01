package simulator.handler;

import exception.SimulatorException;
import message.DMAMsg;
import simulator.connectivity.MessageSenderService;
import util.SimulatorLogger;
import simulator.connectivity.ConnectionState;

import java.lang.reflect.InvocationTargetException;

public class SimulatorMessageHandler
{

    private SimulatorLogger logger;
    private MessageSenderService senderService;
    private ConnectionState connectionState;

    public SimulatorMessageHandler(SimulatorLogger logger, MessageSenderService senderService, ConnectionState connectionState)
    {
        super();
        this.logger = logger;
        this.senderService = senderService;
        this.connectionState = connectionState;
    }

    public void handleMessage(DMAMsg msg)
    {
        try
        {
            String messageType = msg.getHeader().getMessageType();
            MessageHandler handler = MessageHandlerFactory.createHandler(messageType, senderService, connectionState, logger);
            handler.handle(msg);
        }
        catch (SimulatorException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e)
        {
            logger.error(e);
        }
    }
}
