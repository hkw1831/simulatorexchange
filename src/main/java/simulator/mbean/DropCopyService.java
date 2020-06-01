package simulator.mbean;

import exception.MessageCodecException;
import message.DMAMsg;
import message.DMAMsgOrderType;
import message.DMAMsgSide;
import samplemessage.DMAMsgSample;
import simulator.connectivity.MessageSenderService;
import util.IdGenerator;

import java.io.IOException;

public class DropCopyService implements DropCopyServiceMBean
{

    private final MessageSenderService senderService;

    public DropCopyService(MessageSenderService senderService)
    {
        super();
        this.senderService = senderService;
    }

    @Override
    public void sendDropCopy(String side, Integer orderQuantity, Integer price, String orderType) throws IOException, MessageCodecException
    {
        DMAMsg msg = DMAMsgSample.newExecReport(IdGenerator.getId(), side, orderQuantity, price, orderType);
        senderService.sendMessage(msg);
    }

    @Override
    public void sendSampleDropCopy() throws IOException, MessageCodecException
    {
        sendDropCopy(DMAMsgSide.BUY, 3, 10, DMAMsgOrderType.LIMIT);
    }

}

