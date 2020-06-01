package simulator.messagegenerator;

import message.*;
import simulator.matchingengine.DMAMsgDatabase;

public class SimulatorDMAMsgCancelExecutionReportGenerator extends SimulatorDMAMsgGenerator
{

    public SimulatorDMAMsgCancelExecutionReportGenerator(DMAMsg msg)
    {
        super(msg);
    }

    @Override
    protected DMAMsgBodyExecutionReport createBody()
    {

        DMAMsgBodyExecutionReport execRpt = new DMAMsgBodyExecutionReport();
        DMAMsgBodyCancelOrder cancelOrderBody = (DMAMsgBodyCancelOrder) (msg.getBody());
        String origClOrdId = cancelOrderBody.getOriginalClientOrderId();
        String orderId = DMAMsgDatabase.getOrdIdFromClOrdId(origClOrdId);
        DMAMsg originalMsg = DMAMsgDatabase.getMsgFromOrderId(orderId);

        if (originalMsg == null)
        {
            execRpt.setClientOrderId(cancelOrderBody.getClientOrderId());
            execRpt.setOriginalClientOrderId(cancelOrderBody.getOriginalClientOrderId());
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
            execRpt.setRejectCode(DMAMsgRejectCode.ORDER_NOT_FOUND);
            return execRpt;
        }

        DMAMsgBodyExecutionReport execReportBody = (DMAMsgBodyExecutionReport) (originalMsg.getBody());

        execRpt.setClientOrderId(cancelOrderBody.getClientOrderId());
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

        if (cancelOrderBody.getClientOrderId().startsWith("REJ"))
        {
            execRpt.setExecType(DMAMsgExecType.REJECT);
            execRpt.setRejectCode(DMAMsgRejectCode.INVALID_CLORDID);
        }
        else
        {
            execRpt.setExecType(DMAMsgExecType.CANCEL);
            execRpt.setRejectCode(DMAMsgRejectCode.NORMAL);
        }
        return execRpt;
    }
}
