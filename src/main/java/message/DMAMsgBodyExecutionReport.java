package message;

import exception.MessageCodecException;
import util.EncodeDecodeUtil;

public class DMAMsgBodyExecutionReport extends DMAMsgBody
{
    private static final int CLIENT_ORDER_ID_INDEX = 0;
    private static final int ORIGINAL_CLIENT_ORDER_ID_INDEX = CLIENT_ORDER_ID_INDEX + DMAMsgSize.STRING_SIZE;
    private static final int INSTRUMENT_ID_INDEX = ORIGINAL_CLIENT_ORDER_ID_INDEX + DMAMsgSize.STRING_SIZE;
    private static final int SIDE_INDEX = INSTRUMENT_ID_INDEX + DMAMsgSize.STRING_SIZE;
    private static final int ORDER_ID_INDEX = SIDE_INDEX + DMAMsgSize.STRING_SIZE;
    private static final int ORDER_TYPE_INDEX = ORDER_ID_INDEX + DMAMsgSize.STRING_SIZE;
    private static final int ORDER_QUANTITY = ORDER_TYPE_INDEX + DMAMsgSize.STRING_SIZE;
    private static final int PRICE_INDEX = ORDER_QUANTITY + DMAMsgSize.INT_SIZE;
    private static final int TIME_IN_FORCE_INDEX = PRICE_INDEX + DMAMsgSize.INT_SIZE;
    private static final int EXEC_TYPE_INDEX = TIME_IN_FORCE_INDEX + DMAMsgSize.STRING_SIZE;
    private static final int CUMULATIVE_QUANTITY_INDEX = EXEC_TYPE_INDEX + DMAMsgSize.STRING_SIZE;
    private static final int LEAVES_QUANTITY_INDEX = CUMULATIVE_QUANTITY_INDEX + DMAMsgSize.INT_SIZE;
    private static final int LAST_QUANTITY_INDEX = LEAVES_QUANTITY_INDEX + DMAMsgSize.INT_SIZE;
    private static final int LAST_PRICE_INDEX = LAST_QUANTITY_INDEX + DMAMsgSize.INT_SIZE;
    private static final int REJECT_CODE_INDEX = LAST_PRICE_INDEX + DMAMsgSize.INT_SIZE;
    public static final int MESSAGE_SIZE = REJECT_CODE_INDEX + DMAMsgSize.INT_SIZE;

    private String clientOrderId;
    private String originalClientOrderId;
    private String instrumentId;
    private String side;
    private String orderId;
    private String orderType;
    private Integer orderQuantity;
    private Integer price;
    private String timeInForce;
    private String execType;
    private Integer cumulativeQuantity;
    private Integer leavesQuantity;
    private Integer lastQuantity;
    private Integer lastPrice;
    private Integer rejectCode;

    public DMAMsgBodyExecutionReport()
    {
    }

    public DMAMsgBodyExecutionReport(String clientOrderId, String originalClientOrderId, String instrumentId, String side, String orderId, String orderType, Integer orderQuantity, Integer price, String timeInForce, String execType,
                                     Integer cumulativeQuantity, Integer leavesQuantity, Integer lastQuantity, Integer lastPrice, Integer rejectCode)
    {
        this.clientOrderId = clientOrderId;
        this.originalClientOrderId = originalClientOrderId;
        this.instrumentId = instrumentId;
        this.side = side;
        this.orderId = orderId;
        this.orderType = orderType;
        this.orderQuantity = orderQuantity;
        this.price = price;
        this.timeInForce = timeInForce;
        this.execType = execType;
        this.cumulativeQuantity = cumulativeQuantity;
        this.leavesQuantity = leavesQuantity;
        this.lastQuantity = lastQuantity;
        this.lastPrice = lastPrice;
        this.rejectCode = rejectCode;
    }

    @Override
    public byte[] encode() throws MessageCodecException
    {
        byte[] result = new byte[MESSAGE_SIZE];
        byte[] clientOrderIdByte = EncodeDecodeUtil.encodeString(clientOrderId);
        byte[] originalClientOrderIdByte = EncodeDecodeUtil.encodeString(originalClientOrderId);
        byte[] instrumentIdByte = EncodeDecodeUtil.encodeString(instrumentId);
        byte[] sideByte = EncodeDecodeUtil.encodeString(side);
        byte[] orderIdByte = EncodeDecodeUtil.encodeString(orderId);
        byte[] orderTypeByte = EncodeDecodeUtil.encodeString(orderType);
        byte[] orderQuantityByte = EncodeDecodeUtil.encodeInt(orderQuantity);
        byte[] priceByte = EncodeDecodeUtil.encodeInt(price);
        byte[] timeInForceByte = EncodeDecodeUtil.encodeString(timeInForce);
        byte[] execTypeByte = EncodeDecodeUtil.encodeString(execType);
        byte[] cumulativeQuantityByte = EncodeDecodeUtil.encodeInt(cumulativeQuantity);
        byte[] leavesQuantityByte = EncodeDecodeUtil.encodeInt(leavesQuantity);
        byte[] lastQuantityByte = EncodeDecodeUtil.encodeInt(lastQuantity);
        byte[] lastPriceByte = EncodeDecodeUtil.encodeInt(lastPrice);
        byte[] rejectCodeByte = EncodeDecodeUtil.encodeInt(rejectCode);

        System.arraycopy(clientOrderIdByte, 0, result, CLIENT_ORDER_ID_INDEX, clientOrderIdByte.length);
        System.arraycopy(originalClientOrderIdByte, 0, result, ORIGINAL_CLIENT_ORDER_ID_INDEX, originalClientOrderIdByte.length);
        System.arraycopy(instrumentIdByte, 0, result, INSTRUMENT_ID_INDEX, instrumentIdByte.length);
        System.arraycopy(sideByte, 0, result, SIDE_INDEX, sideByte.length);
        System.arraycopy(orderIdByte, 0, result, ORDER_ID_INDEX, orderIdByte.length);
        System.arraycopy(orderTypeByte, 0, result, ORDER_TYPE_INDEX, orderTypeByte.length);
        System.arraycopy(orderQuantityByte, 0, result, ORDER_QUANTITY, orderQuantityByte.length);
        System.arraycopy(priceByte, 0, result, PRICE_INDEX, priceByte.length);
        System.arraycopy(timeInForceByte, 0, result, TIME_IN_FORCE_INDEX, timeInForceByte.length);
        System.arraycopy(execTypeByte, 0, result, EXEC_TYPE_INDEX, execTypeByte.length);
        System.arraycopy(cumulativeQuantityByte, 0, result, CUMULATIVE_QUANTITY_INDEX, cumulativeQuantityByte.length);
        System.arraycopy(leavesQuantityByte, 0, result, LEAVES_QUANTITY_INDEX, leavesQuantityByte.length);
        System.arraycopy(lastQuantityByte, 0, result, LAST_QUANTITY_INDEX, lastQuantityByte.length);
        System.arraycopy(lastPriceByte, 0, result, LAST_PRICE_INDEX, lastPriceByte.length);
        System.arraycopy(rejectCodeByte, 0, result, REJECT_CODE_INDEX, rejectCodeByte.length);

        return result;
    }

    @Override
    public void decode(byte[] input) throws MessageCodecException
    {
        byte[] clientOrderIdByte = new byte[DMAMsgSize.STRING_SIZE];
        byte[] originalClientOrderIdByte = new byte[DMAMsgSize.STRING_SIZE];
        byte[] instrumentIdByte = new byte[DMAMsgSize.STRING_SIZE];
        byte[] sideByte = new byte[DMAMsgSize.STRING_SIZE];
        byte[] orderIdByte = new byte[DMAMsgSize.STRING_SIZE];
        byte[] orderTypeByte = new byte[DMAMsgSize.STRING_SIZE];
        byte[] orderQuantityByte = new byte[DMAMsgSize.INT_SIZE];
        byte[] priceByte = new byte[DMAMsgSize.INT_SIZE];
        byte[] timeInForceByte = new byte[DMAMsgSize.STRING_SIZE];
        byte[] execTypeByte = new byte[DMAMsgSize.STRING_SIZE];
        byte[] cumulativeQuantityByte = new byte[DMAMsgSize.INT_SIZE];
        byte[] leavesQuantityByte = new byte[DMAMsgSize.INT_SIZE];
        byte[] lastQuantityByte = new byte[DMAMsgSize.INT_SIZE];
        byte[] lastPriceByte = new byte[DMAMsgSize.INT_SIZE];
        byte[] rejectCodeByte = new byte[DMAMsgSize.INT_SIZE];

        System.arraycopy(input, CLIENT_ORDER_ID_INDEX, clientOrderIdByte, 0, clientOrderIdByte.length);
        System.arraycopy(input, ORIGINAL_CLIENT_ORDER_ID_INDEX, originalClientOrderIdByte, 0, originalClientOrderIdByte.length);
        System.arraycopy(input, INSTRUMENT_ID_INDEX, instrumentIdByte, 0, instrumentIdByte.length);
        System.arraycopy(input, SIDE_INDEX, sideByte, 0, sideByte.length);
        System.arraycopy(input, ORDER_ID_INDEX, orderIdByte, 0, orderIdByte.length);
        System.arraycopy(input, ORDER_TYPE_INDEX, orderTypeByte, 0, orderTypeByte.length);
        System.arraycopy(input, ORDER_QUANTITY, orderQuantityByte, 0, orderQuantityByte.length);
        System.arraycopy(input, PRICE_INDEX, priceByte, 0, priceByte.length);
        System.arraycopy(input, TIME_IN_FORCE_INDEX, timeInForceByte, 0, timeInForceByte.length);
        System.arraycopy(input, EXEC_TYPE_INDEX, execTypeByte, 0, execTypeByte.length);
        System.arraycopy(input, CUMULATIVE_QUANTITY_INDEX, cumulativeQuantityByte, 0, cumulativeQuantityByte.length);
        System.arraycopy(input, LEAVES_QUANTITY_INDEX, leavesQuantityByte, 0, leavesQuantityByte.length);
        System.arraycopy(input, LAST_QUANTITY_INDEX, lastQuantityByte, 0, lastQuantityByte.length);
        System.arraycopy(input, LAST_PRICE_INDEX, lastPriceByte, 0, lastPriceByte.length);
        System.arraycopy(input, REJECT_CODE_INDEX, rejectCodeByte, 0, rejectCodeByte.length);

        clientOrderId = EncodeDecodeUtil.decodeString(clientOrderIdByte);
        originalClientOrderId = EncodeDecodeUtil.decodeString(originalClientOrderIdByte);
        instrumentId = EncodeDecodeUtil.decodeString(instrumentIdByte);
        side = EncodeDecodeUtil.decodeString(sideByte);
        orderId = EncodeDecodeUtil.decodeString(orderIdByte);
        orderType = EncodeDecodeUtil.decodeString(orderTypeByte);
        orderQuantity = EncodeDecodeUtil.decodeInt(orderQuantityByte);
        price = EncodeDecodeUtil.decodeInt(priceByte);
        timeInForce = EncodeDecodeUtil.decodeString(timeInForceByte);
        execType = EncodeDecodeUtil.decodeString(execTypeByte);
        cumulativeQuantity = EncodeDecodeUtil.decodeInt(cumulativeQuantityByte);
        leavesQuantity = EncodeDecodeUtil.decodeInt(leavesQuantityByte);
        lastQuantity = EncodeDecodeUtil.decodeInt(lastQuantityByte);
        lastPrice = EncodeDecodeUtil.decodeInt(lastPriceByte);
        rejectCode = EncodeDecodeUtil.decodeInt(rejectCodeByte);

    }

    @Override
    public int size()
    {
        return MESSAGE_SIZE;
    }

    public String getClientOrderId()
    {
        return clientOrderId;
    }

    public void setClientOrderId(String clientOrderId)
    {
        this.clientOrderId = clientOrderId;
    }

    public String getOriginalClientOrderId()
    {
        return originalClientOrderId;
    }

    public void setOriginalClientOrderId(String originalClientOrderId)
    {
        this.originalClientOrderId = originalClientOrderId;
    }

    public String getInstrumentId()
    {
        return instrumentId;
    }

    public void setInstrumentId(String instrumentId)
    {
        this.instrumentId = instrumentId;
    }

    public String getSide()
    {
        return side;
    }

    public void setSide(String side)
    {
        this.side = side;
    }

    public String getOrderId()
    {
        return orderId;
    }

    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
    }

    public String getOrderType()
    {
        return orderType;
    }

    public void setOrderType(String orderType)
    {
        this.orderType = orderType;
    }

    public Integer getOrderQuantity()
    {
        return orderQuantity;
    }

    public void setOrderQuantity(Integer orderQuantity)
    {
        this.orderQuantity = orderQuantity;
    }

    public Integer getPrice()
    {
        return price;
    }

    public void setPrice(Integer price)
    {
        this.price = price;
    }

    public String getTimeInForce()
    {
        return timeInForce;
    }

    public void setTimeInForce(String timeInForce)
    {
        this.timeInForce = timeInForce;
    }

    public String getExecType()
    {
        return execType;
    }

    public void setExecType(String execType)
    {
        this.execType = execType;
    }

    public Integer getCumulativeQuantity()
    {
        return cumulativeQuantity;
    }

    public void setCumulativeQuantity(Integer cumulativeQuantity)
    {
        this.cumulativeQuantity = cumulativeQuantity;
    }

    public Integer getLeavesQuantity()
    {
        return leavesQuantity;
    }

    public void setLeavesQuantity(Integer leavesQuantity)
    {
        this.leavesQuantity = leavesQuantity;
    }

    public Integer getRejectCode()
    {
        return rejectCode;
    }

    public void setRejectCode(Integer rejectCode)
    {
        this.rejectCode = rejectCode;
    }

    public Integer getLastQuantity()
    {
        return lastQuantity;
    }

    public void setLastQuantity(Integer lastQuantity)
    {
        this.lastQuantity = lastQuantity;
    }

    public Integer getLastPrice()
    {
        return lastPrice;
    }

    public void setLastPrice(Integer lastPrice)
    {
        this.lastPrice = lastPrice;
    }

    @Override
    public String toString()
    {
        return "DMAMsgBodyExecutionReport [clientOrderId=" + clientOrderId + ", originalClientOrderId=" + originalClientOrderId + ", instrumentId=" + instrumentId + ", side=" + side + ", orderId=" + orderId + ", orderType=" + orderType
                + ", orderQuantity=" + orderQuantity + ", price=" + price + ", timeInForce=" + timeInForce + ", execType=" + execType + ", cumulativeQuantity=" + cumulativeQuantity + ", leavesQuantity=" + leavesQuantity + ", lastQuantity="
                + lastQuantity + ", lastPrice=" + lastPrice + ", rejectCode=" + rejectCode + "]";
    }

    @Override
    public Object clone()
    {
        return new DMAMsgBodyExecutionReport(clientOrderId, originalClientOrderId, instrumentId, side, clientOrderId, orderType, orderQuantity, price, timeInForce, execType, cumulativeQuantity, leavesQuantity, lastQuantity, lastPrice,
                rejectCode);
    }

}
