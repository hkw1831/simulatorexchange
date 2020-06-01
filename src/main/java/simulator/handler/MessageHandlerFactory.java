package simulator.handler;

import message.DMAMsgType;
import simulator.connectivity.ConnectionState;
import simulator.connectivity.MessageSenderService;
import util.SimulatorLogger;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class MessageHandlerFactory
{
    public static final Map<String, Class<?>> handlerMap;
    static
    {
        handlerMap = new HashMap<>();
        handlerMap.put(DMAMsgType.LOGOUT, LogoutMessageHandler.class);
        handlerMap.put(DMAMsgType.LOGON, LogonMessageHandler.class);
        handlerMap.put(DMAMsgType.NEW_ORDER, NewOrderMessageHandler.class);
        handlerMap.put(DMAMsgType.CANCEL_ORDER, CancelOrderMessageHandler.class);
        handlerMap.put(DMAMsgType.REPLACE_ORDER, ReplaceOrderMessageHandler.class);
    }

    public static MessageHandler createHandler(String messageType, MessageSenderService senderService, ConnectionState connectable, SimulatorLogger logger)
            throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException
    {
        Class<?> handlerClass = handlerMap.get(messageType);
        if (handlerClass == null)
        {
            handlerClass = DefaultMessageHandler.class;
        }
        MessageHandler handler = (MessageHandler) (handlerClass.getConstructor(ConnectionState.class, MessageSenderService.class, SimulatorLogger.class)
                .newInstance(connectable, senderService, logger));
        return handler;
    }
}
