package samplemessage;

import message.*;

public class DMAMsgSample
{
    public static DMAMsg newExecReport(String tradeName, String side, Integer orderQuantity, Integer price, String orderType)
    {
        DMAMsg msg = new DMAMsg();
        DMAMsgHeader header = new DMAMsgHeader();
        header.setLength(338);
        header.setMessageType(DMAMsgType.EXECUTION_REPORT);
        header.setSequenceNumber(2);
        header.setUsername("username");

        DMAMsgBodyExecutionReport execReport = new DMAMsgBodyExecutionReport();
        execReport.setClientOrderId("clOrd" + tradeName);
        execReport.setOriginalClientOrderId("clOrd" + tradeName);
        execReport.setInstrumentId("ULLINK");
        execReport.setSide(side);
        execReport.setOrderId("ord" + tradeName);
        execReport.setOrderType(orderType);
        execReport.setOrderQuantity(orderQuantity);
        execReport.setPrice(price);
        execReport.setTimeInForce(DMAMsgTimeInForce.DAY);
        execReport.setExecType(DMAMsgExecType.NEW);
        execReport.setCumulativeQuantity(0);
        execReport.setLeavesQuantity(orderQuantity);
        execReport.setLastQuantity(0);
        execReport.setLastPrice(0);
        execReport.setRejectCode(DMAMsgRejectCode.NORMAL);
        msg.setHeader(header);
        msg.setBody(execReport);
        return msg;
    }
}