package simulator.connectivity;

import exception.MessageCodecException;
import message.DMAMsg;
import util.SimulatorLogger;

import java.io.DataOutputStream;
import java.io.IOException;

public class MessageSenderService
{

    private SimulatorLogger logger;
    private DataOutputStream out;
    private SequenceNumberCount sequenceNumberCount;

    public MessageSenderService(SimulatorLogger logger, DataOutputStream out, SequenceNumberCount sequenceNumberCount)
    {
        super();
        this.logger = logger;
        this.out = out;
        this.sequenceNumberCount = sequenceNumberCount;
    }

    public void sendMessage(DMAMsg msg) throws IOException, MessageCodecException
    {
        msg.getHeader().setSequenceNumber(sequenceNumberCount.nextSequenceNumber());
        logger.traceSendingMessage(msg);
        out.write(msg.encode());
    }
}
