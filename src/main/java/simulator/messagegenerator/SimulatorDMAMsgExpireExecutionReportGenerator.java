package simulator.messagegenerator;

import message.DMAMsg;
import message.DMAMsgBodyExecutionReport;
import message.DMAMsgExecType;
import message.DMAMsgRejectCode;

public class SimulatorDMAMsgExpireExecutionReportGenerator extends SimulatorDMAMsgGenerator
{

    public SimulatorDMAMsgExpireExecutionReportGenerator(DMAMsg msg)
    {
        super(msg);
    }

    @Override
    protected DMAMsgBodyExecutionReport createBody()
    {
        DMAMsgBodyExecutionReport execRpt = new DMAMsgBodyExecutionReport();
        DMAMsgBodyExecutionReport execReportBody = (DMAMsgBodyExecutionReport) (msg.getBody());

        execRpt.setClientOrderId(execReportBody.getClientOrderId());
        execRpt.setOriginalClientOrderId(execReportBody.getOriginalClientOrderId());
        execRpt.setInstrumentId(execReportBody.getInstrumentId());
        execRpt.setSide(execReportBody.getSide());
        execRpt.setOrderId(execReportBody.getOrderId());
        execRpt.setOrderType(execReportBody.getOrderType());
        execRpt.setOrderQuantity(execReportBody.getOrderQuantity());
        execRpt.setPrice(execReportBody.getPrice());
        execRpt.setTimeInForce(execReportBody.getTimeInForce());
        execRpt.setCumulativeQuantity(execReportBody.getCumulativeQuantity());
        execRpt.setLeavesQuantity(execReportBody.getLeavesQuantity());
        execRpt.setLastPrice(execReportBody.getLastPrice());
        execRpt.setLastQuantity(execReportBody.getLastQuantity());

        execRpt.setExecType(DMAMsgExecType.CANCEL);
        execRpt.setRejectCode(DMAMsgRejectCode.NORMAL);
        return execRpt;
    }
}

