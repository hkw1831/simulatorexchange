package util;

import message.DMAMsg;

import java.util.Date;

public class SimulatorLogger
{
    private static final String SEND = " Send   : ";
    private static final String RECEIVE = " Receive: ";
    private static final String CLIENT_LOGOUT = " Client Logout";
    private static final String CLIENT_LOGON = " Client Logon";
    private static final String STORING = " Storing: ";
    private static final String DELETING = " Deleting: ";

    public void traceSendingMessage(DMAMsg msg)
    {
        System.out.println(new Date() + SEND + msg);
    }

    public void traceReceivingMessage(DMAMsg msg)
    {
        System.out.println(new Date() + RECEIVE + msg);
    }

    public void traceClientLogon()
    {
        System.out.println(new Date() + CLIENT_LOGON);
    }

    public void traceClientLogout()
    {
        System.out.println(new Date() + CLIENT_LOGOUT);
    }

    public void error(Throwable e)
    {
        e.printStackTrace();
    }

    public void traceStoreMessage(String clOrdId, DMAMsg msg)
    {
        System.out.println(new Date() + STORING + "{ " + clOrdId + ", " + msg + " }");
    }

    public void traceStoreMessageId(String clOrdId, String ordId)
    {
        System.out.println(new Date() + STORING + "{ " + clOrdId + ", " + ordId + " } ");
    }

    public void traceDeleteMessage(String clOrdId, DMAMsg msg)
    {
        System.out.println(new Date() + DELETING + "{ " + clOrdId + ", " + msg + " }");
    }

    public void traceDeleteMessageId(String clOrdId, String ordId)
    {
        System.out.println(new Date() + DELETING + "{ " + clOrdId + ", " + ordId + " } ");
    }
}
