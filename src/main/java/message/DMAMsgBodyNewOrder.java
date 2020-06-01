package message;

import exception.MessageCodecException;
import util.EncodeDecodeUtil;

public class DMAMsgBodyNewOrder extends DMAMsgBody
{

    private static final int CLIENT_ORDER_ID_INDEX = 0;
    private static final int INSTRUMENT_ID_INDEX = CLIENT_ORDER_ID_INDEX + DMAMsgSize.STRING_SIZE;
    private static final int ORDER_TYPE_INDEX = INSTRUMENT_ID_INDEX + DMAMsgSize.STRING_SIZE;
    private static final int SIDE_INDEX = ORDER_TYPE_INDEX + DMAMsgSize.STRING_SIZE;
    private static final int ORDER_QUANTITY = SIDE_INDEX + DMAMsgSize.STRING_SIZE;
    private static final int PRICE_INDEX = ORDER_QUANTITY + DMAMsgSize.INT_SIZE;
    private static final int TIME_IN_FORCE_INDEX = PRICE_INDEX + DMAMsgSize.INT_SIZE;
    private static final int TRANSACTION_TIME_INDEX = TIME_IN_FORCE_INDEX + DMAMsgSize.STRING_SIZE;
    public static final int MESSAGE_SIZE = TRANSACTION_TIME_INDEX + DMAMsgSize.STRING_SIZE;

    private String clientOrderId;
    private String instrumentId;
    private String orderType;
    private String side;
    private Integer orderQuantity;
    private Integer price;
    private String timeInForce;
    private String transactionTime;

    public DMAMsgBodyNewOrder()
    {
    }

    public DMAMsgBodyNewOrder(String clientOrderId, String instrumentId, String orderType, String side, Integer orderQuantity, Integer price, String timeInForce, String transactionTime)
    {
        super();
        this.clientOrderId = clientOrderId;
        this.instrumentId = instrumentId;
        this.orderType = orderType;
        this.side = side;
        this.orderQuantity = orderQuantity;
        this.price = price;
        this.timeInForce = timeInForce;
        this.transactionTime = transactionTime;
    }

    @Override
    public byte[] encode() throws MessageCodecException
    {
        byte[] result = new byte[MESSAGE_SIZE];
        byte[] clientOrderIdByte = EncodeDecodeUtil.encodeString(clientOrderId);
        byte[] instrumentIdByte = EncodeDecodeUtil.encodeString(instrumentId);
        byte[] orderTypeByte = EncodeDecodeUtil.encodeString(orderType);
        byte[] sideByte = EncodeDecodeUtil.encodeString(side);
        byte[] orderQuantityByte = EncodeDecodeUtil.encodeInt(orderQuantity);
        byte[] priceByte = EncodeDecodeUtil.encodeInt((price != null) ? price : 0);
        byte[] timeInForceByte = EncodeDecodeUtil.encodeString(timeInForce);
        byte[] transactionTimeByte = EncodeDecodeUtil.encodeString(transactionTime);

        System.arraycopy(clientOrderIdByte, 0, result, CLIENT_ORDER_ID_INDEX, clientOrderIdByte.length);
        System.arraycopy(instrumentIdByte, 0, result, INSTRUMENT_ID_INDEX, instrumentIdByte.length);
        System.arraycopy(orderTypeByte, 0, result, ORDER_TYPE_INDEX, orderTypeByte.length);
        System.arraycopy(sideByte, 0, result, SIDE_INDEX, sideByte.length);
        System.arraycopy(orderQuantityByte, 0, result, ORDER_QUANTITY, orderQuantityByte.length);
        System.arraycopy(priceByte, 0, result, PRICE_INDEX, priceByte.length);
        System.arraycopy(timeInForceByte, 0, result, TIME_IN_FORCE_INDEX, timeInForceByte.length);
        System.arraycopy(transactionTimeByte, 0, result, TRANSACTION_TIME_INDEX, transactionTimeByte.length);

        return result;
    }

    @Override
    public void decode(byte[] input) throws MessageCodecException
    {
        byte[] clientOrderIdByte = new byte[DMAMsgSize.STRING_SIZE];
        byte[] instrumentIdByte = new byte[DMAMsgSize.STRING_SIZE];
        byte[] orderTypeByte = new byte[DMAMsgSize.STRING_SIZE];
        byte[] sideByte = new byte[DMAMsgSize.STRING_SIZE];
        byte[] orderQuantityByte = new byte[DMAMsgSize.INT_SIZE];
        byte[] priceByte = new byte[DMAMsgSize.INT_SIZE];
        byte[] timeInForceByte = new byte[DMAMsgSize.STRING_SIZE];
        byte[] transactionTimeByte = new byte[DMAMsgSize.STRING_SIZE];

        System.arraycopy(input, CLIENT_ORDER_ID_INDEX, clientOrderIdByte, 0, clientOrderIdByte.length);
        System.arraycopy(input, INSTRUMENT_ID_INDEX, instrumentIdByte, 0, instrumentIdByte.length);
        System.arraycopy(input, ORDER_TYPE_INDEX, orderTypeByte, 0, orderTypeByte.length);
        System.arraycopy(input, SIDE_INDEX, sideByte, 0, sideByte.length);
        System.arraycopy(input, ORDER_QUANTITY, orderQuantityByte, 0, orderQuantityByte.length);
        System.arraycopy(input, PRICE_INDEX, priceByte, 0, priceByte.length);
        System.arraycopy(input, TIME_IN_FORCE_INDEX, timeInForceByte, 0, timeInForceByte.length);
        System.arraycopy(input, TRANSACTION_TIME_INDEX, transactionTimeByte, 0, transactionTimeByte.length);

        clientOrderId = EncodeDecodeUtil.decodeString(clientOrderIdByte);
        instrumentId = EncodeDecodeUtil.decodeString(instrumentIdByte);
        orderType = EncodeDecodeUtil.decodeString(orderTypeByte);
        side = EncodeDecodeUtil.decodeString(sideByte);
        orderQuantity = EncodeDecodeUtil.decodeInt(orderQuantityByte);
        price = EncodeDecodeUtil.decodeInt(priceByte);
        timeInForce = EncodeDecodeUtil.decodeString(timeInForceByte);
        transactionTime = EncodeDecodeUtil.decodeString(transactionTimeByte);
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

    public String getInstrumentId()
    {
        return instrumentId;
    }

    public void setInstrumentId(String instrumentId)
    {
        this.instrumentId = instrumentId;
    }

    public String getOrderType()
    {
        return orderType;
    }

    public void setOrderType(String orderType)
    {
        this.orderType = orderType;
    }

    public String getSide()
    {
        return side;
    }

    public void setSide(String side)
    {
        this.side = side;
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

    public String getTransactionTime()
    {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime)
    {
        this.transactionTime = transactionTime;
    }

    @Override
    public String toString()
    {
        return "DMAMsgNewOrder [clientOrderId=" + clientOrderId + ", instrumentId=" + instrumentId + ", orderType=" + orderType + ", side=" + side + ", orderQuantity=" + orderQuantity + ", price=" + price + ", timeInForce=" + timeInForce
                + ", transactionTime=" + transactionTime + "]";
    }

    @Override
    public Object clone()
    {
        return new DMAMsgBodyNewOrder(clientOrderId, instrumentId, orderType, side, orderQuantity, price, timeInForce, transactionTime);
    }

}
