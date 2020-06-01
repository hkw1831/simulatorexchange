package simulator.messagegenerator;

import message.DMAMsg;
import message.DMAMsgBodyExecutionReport;
import message.DMAMsgExecType;
import message.DMAMsgRejectCode;

public class SimulatorDMAMsgInvalidMessageTypeReportGenerator extends SimulatorDMAMsgGenerator
{

    public SimulatorDMAMsgInvalidMessageTypeReportGenerator(DMAMsg msg)
    {
        super(msg);
    }

    @Override
    protected DMAMsgBodyExecutionReport createBody()
    {
        DMAMsgBodyExecutionReport execRpt = new DMAMsgBodyExecutionReport();
        msg.getBody();
        msg.getHeader();

        execRpt.setClientOrderId("");
        execRpt.setOriginalClientOrderId("");
        execRpt.setInstrumentId("");
        execRpt.setSide("");
        execRpt.setOrderId("");
        execRpt.setOrderType("");
        execRpt.setOrderQuantity(0);
        execRpt.setPrice(0);
        execRpt.setTimeInForce("");
        execRpt.setCumulativeQuantity(0);
        execRpt.setLeavesQuantity(0);
        execRpt.setLastPrice(0);
        execRpt.setLastQuantity(0);

        execRpt.setExecType(DMAMsgExecType.REJECT);
        execRpt.setRejectCode(DMAMsgRejectCode.INVALID_MSG_TYPE);
        return execRpt;
    }

}

