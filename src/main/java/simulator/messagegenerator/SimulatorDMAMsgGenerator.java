package simulator.messagegenerator;

import message.DMAMsg;
import message.DMAMsgBodyExecutionReport;
import message.DMAMsgHeader;
import message.DMAMsgType;

public abstract class SimulatorDMAMsgGenerator
{

    protected DMAMsg msg;

    public SimulatorDMAMsgGenerator(DMAMsg msg)
    {
        this.msg = msg;
    }

    public DMAMsg createExecutionReoprt()
    {
        DMAMsgHeader header = createHeader();
        DMAMsgBodyExecutionReport body = createBody();
        if (body == null)
        {
            return null;
        }
        else
        {
            DMAMsg message = createMessage(header, body);
            return message;
        }
    }

    abstract protected DMAMsgBodyExecutionReport createBody();

    protected DMAMsgHeader createHeader()
    {
        DMAMsgHeader header = new DMAMsgHeader();
        header.setMessageType(DMAMsgType.EXECUTION_REPORT);
        header.setSequenceNumber(msg.getHeader().getSequenceNumber());
        header.setUsername(msg.getHeader().getUsername());
        return header;
    }

    protected DMAMsg createMessage(DMAMsgHeader header, DMAMsgBodyExecutionReport execRpt)
    {
        DMAMsg msg = new DMAMsg();
        msg.setHeader(header);
        msg.setBody(execRpt);
        msg.getHeader().setLength(header.size() + execRpt.size());
        return msg;
    }

}
