package message;

import exception.MessageCodecException;

import java.util.HashMap;
import java.util.Map;

public class DMAMsgBodyFactory
{
    private static final Map<String, Class<?>> msgBodyMap;

    static
    {
        msgBodyMap = new HashMap<String, Class<?>>();
        msgBodyMap.put(DMAMsgType.LOGON, DMAMsgBodyLogon.class);
        msgBodyMap.put(DMAMsgType.LOGOUT, DMAMsgBodyLogout.class);
        msgBodyMap.put(DMAMsgType.NEW_ORDER, DMAMsgBodyNewOrder.class);
        msgBodyMap.put(DMAMsgType.CANCEL_ORDER, DMAMsgBodyCancelOrder.class);
        msgBodyMap.put(DMAMsgType.REPLACE_ORDER, DMAMsgBodyReplaceOrder.class);
        msgBodyMap.put(DMAMsgType.EXECUTION_REPORT, DMAMsgBodyExecutionReport.class);
    }

    public static DMAMsgBody createDMAMsgBody(String msgType) throws MessageCodecException
    {
        Class<?> bodyClass = msgBodyMap.get(msgType);
        if (bodyClass == null)
        {
            return new DMAMsgBodyNullObject();
        }
        try
        {
            return (DMAMsgBody) (bodyClass.newInstance());
        }
        catch (InstantiationException | IllegalAccessException e)
        {
            throw new MessageCodecException("Failed to instantiate MsgBody with type " + msgType);
        }
    }
}
