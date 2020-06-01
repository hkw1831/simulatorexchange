package simulator.messagegenerator;

import message.*;
import simulator.matchingengine.DMAMsgDatabase;

public class SimulatorDMAMsgReplaceExecutionReportGenerator extends SimulatorDMAMsgGenerator
{

    public SimulatorDMAMsgReplaceExecutionReportGenerator(DMAMsg msg)
    {
        super(msg);
    }

    @Override
    protected DMAMsgBodyExecutionReport createBody()
    {
        boolean reject = false;
        DMAMsgBodyExecutionReport execRpt = new DMAMsgBodyExecutionReport();
        DMAMsgBodyReplaceOrder replaceOrderBody = (DMAMsgBodyReplaceOrder) (msg.getBody());
        String origClOrdId = replaceOrderBody.getOriginalClientOrderId();
        String orderId = DMAMsgDatabase.getOrdIdFromClOrdId(origClOrdId);
        DMAMsg originalMsg = DMAMsgDatabase.getMsgFromOrderId(orderId);

        if (originalMsg == null)
        {
            execRpt.setClientOrderId(replaceOrderBody.getClientOrderId());
            execRpt.setOriginalClientOrderId(replaceOrderBody.getOriginalClientOrderId());
            execRpt.setInstrumentId("");
            execRpt.setSide("");
            execRpt.setOrderId("");
            execRpt.setOrderType("");
            execRpt.setOrderQuantity(replaceOrderBody.getOrderQuantity());
            execRpt.setPrice(replaceOrderBody.getPrice());
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

        execRpt.setClientOrderId(replaceOrderBody.getClientOrderId());
        execRpt.setOriginalClientOrderId(replaceOrderBody.getOriginalClientOrderId());
        execRpt.setInstrumentId(execReportBody.getInstrumentId());
        execRpt.setSide(execReportBody.getSide());
        execRpt.setOrderId(execReportBody.getOrderId());
        execRpt.setOrderType(execReportBody.getOrderType());
        execRpt.setOrderQuantity(replaceOrderBody.getOrderQuantity());
        execRpt.setPrice(replaceOrderBody.getPrice());
        execRpt.setTimeInForce(execReportBody.getTimeInForce());
        execRpt.setCumulativeQuantity(execReportBody.getCumulativeQuantity());
        execRpt.setLeavesQuantity(replaceOrderBody.getOrderQuantity() - execReportBody.getCumulativeQuantity());
        execRpt.setLastPrice(execReportBody.getLastPrice());
        execRpt.setLastQuantity(execReportBody.getLastQuantity());

        if (replaceOrderBody.getClientOrderId().startsWith("REJ"))
        {
            reject = true;
            execRpt.setRejectCode(DMAMsgRejectCode.INVALID_CLORDID);
        }
        if (DMAMsgOrderType.LIMIT.equals(execReportBody.getOrderType()) && new Integer(0).equals(replaceOrderBody.getPrice()))
        {
            reject = true;
            execRpt.setRejectCode(DMAMsgRejectCode.MISSING_PRICE_FOR_LIMIT_ORDER);
        }
        if (DMAMsgOrderType.LIMIT.equals(execReportBody.getOrderType()) && replaceOrderBody.getPrice() < 0)
        {
            reject = true;
            execRpt.setRejectCode(DMAMsgRejectCode.INVALID_PRICE);
        }
        if (DMAMsgOrderType.MARKET.equals(execReportBody.getOrderType()) && !new Integer(0).equals(replaceOrderBody.getPrice()))
        {
            reject = true;
            execRpt.setRejectCode(DMAMsgRejectCode.INVALID_PRICE);
        }
        if (replaceOrderBody.getOrderQuantity() <= 0)
        {
            reject = true;
            execRpt.setRejectCode(DMAMsgRejectCode.INVALID_QUANTITY);
        }

        if (reject)
        {
            execRpt.setExecType(DMAMsgExecType.REJECT);
        }
        else
        {
            execRpt.setExecType(DMAMsgExecType.REPLACE);
            execRpt.setRejectCode(DMAMsgRejectCode.NORMAL);
        }
        return execRpt;
    }
}
