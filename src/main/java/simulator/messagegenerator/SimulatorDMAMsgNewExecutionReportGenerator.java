package simulator.messagegenerator;

import message.*;
import util.IdGenerator;

public class SimulatorDMAMsgNewExecutionReportGenerator extends SimulatorDMAMsgGenerator
{

    public SimulatorDMAMsgNewExecutionReportGenerator(DMAMsg msg)
    {
        super(msg);
    }

    @Override
    protected DMAMsgBodyExecutionReport createBody()
    {
        boolean reject = false;
        DMAMsgBodyExecutionReport execRpt = new DMAMsgBodyExecutionReport();
        DMAMsgBodyNewOrder newOrderBody = (DMAMsgBodyNewOrder) (msg.getBody());
        execRpt.setClientOrderId(newOrderBody.getClientOrderId());
        execRpt.setOriginalClientOrderId(newOrderBody.getClientOrderId());
        execRpt.setInstrumentId(newOrderBody.getInstrumentId());
        execRpt.setSide(newOrderBody.getSide());
        execRpt.setOrderId(IdGenerator.getId());
        execRpt.setOrderType(newOrderBody.getOrderType());
        execRpt.setOrderQuantity(newOrderBody.getOrderQuantity());
        execRpt.setPrice(newOrderBody.getPrice());
        execRpt.setTimeInForce(newOrderBody.getTimeInForce());
        execRpt.setCumulativeQuantity(0);
        execRpt.setLeavesQuantity(newOrderBody.getOrderQuantity());
        execRpt.setLastPrice(0);
        execRpt.setLastQuantity(0);

        if (!DMAMsgOrderType.checkValidValue(newOrderBody.getOrderType()))
        {
            reject = true;
            execRpt.setRejectCode(DMAMsgRejectCode.INVALID_ORDER_TYPE);
        }
        if (!DMAMsgSide.checkValidValue(newOrderBody.getSide()))
        {
            reject = true;
            execRpt.setRejectCode(DMAMsgRejectCode.INVALID_SIDE);
        }
        if (!DMAMsgTimeInForce.checkValidValue(newOrderBody.getTimeInForce()))
        {
            reject = true;
            execRpt.setRejectCode(DMAMsgRejectCode.INVALID_TIME_IN_FORCE);
        }
        if (DMAMsgOrderType.LIMIT.equals(newOrderBody.getOrderType()) && new Integer(0).equals(newOrderBody.getPrice()))
        {
            reject = true;
            execRpt.setRejectCode(DMAMsgRejectCode.MISSING_PRICE_FOR_LIMIT_ORDER);
        }
        if (DMAMsgOrderType.LIMIT.equals(newOrderBody.getOrderType()) && newOrderBody.getPrice() < 0)
        {
            reject = true;
            execRpt.setRejectCode(DMAMsgRejectCode.INVALID_PRICE);
        }
        if (DMAMsgOrderType.MARKET.equals(newOrderBody.getOrderType()) && !new Integer(0).equals(newOrderBody.getPrice()))
        {
            reject = true;
            execRpt.setRejectCode(DMAMsgRejectCode.INVALID_PRICE);
        }
        if (newOrderBody.getOrderQuantity() <= 0)
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
            execRpt.setExecType(DMAMsgExecType.NEW);
            execRpt.setRejectCode(DMAMsgRejectCode.NORMAL);
        }
        return execRpt;
    }

}

