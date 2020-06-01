package simulator.connectivity;

import message.DMAMsg;
import util.SimulatorLogger;
import simulator.handler.SimulatorMessageHandler;
import util.EncodeDecodeUtil;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientThread extends Thread
{
    private DataOutputStream out;
    private DataInputStream in;
    private Socket socket;
    private SimulatorLogger logger;
    private ConnectionState connectionState;
    private SimulatorMessageHandler messageHandler;
    private MessageSenderService senderService;
    private SequenceNumberCount sequenceNumberCount;

    public ClientThread(Socket socket, SimulatorLogger logger)
    {
        this.socket = socket;
        this.logger = logger;
        connectionState = new ConnectionState();
        connectionState.setConnected();
        sequenceNumberCount = new SequenceNumberCount();
        try
        {
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        }
        catch (IOException e)
        {
            return;
        }
        senderService = new MessageSenderService(logger, out, sequenceNumberCount);
        messageHandler = new SimulatorMessageHandler(logger, senderService, connectionState);
    }

    public void closeConnection()
    {
        try
        {
            out.close();
            in.close();
            socket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void run()
    {
        while (connectionState.isConnected())
        {
            handleMessage();
        }
        closeConnection();
    }

    protected void handleMessage()
    {
        try
        {
            byte[] bytes = new byte[2048];
            DMAMsg msg = getReceivedDMAMessage(bytes);
            if (msg != null)
            {
                messageHandler.handleMessage(msg);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    protected DMAMsg getReceivedDMAMessage(byte[] bytes) throws Exception
    {
        in.read(bytes);
        int size = EncodeDecodeUtil.decodeInt(new byte[]{bytes[0], bytes[1]});
        if (size == 0)
        {
            return null;
        }
        byte[] msgBytes = new byte[size];
        System.arraycopy(bytes, 0, msgBytes, 0, size);
        DMAMsg msg = new DMAMsg();
        msg.decode(msgBytes);
        logger.traceReceivingMessage(msg);
        return msg;
    }
}
