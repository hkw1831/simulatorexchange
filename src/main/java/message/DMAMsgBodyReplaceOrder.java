package message;

import exception.MessageCodecException;
import util.EncodeDecodeUtil;

public class DMAMsgBodyReplaceOrder extends DMAMsgBody
{
    private static final int CLIENT_ORDER_ID_INDEX = 0;
    private static final int ORIGINAL_CLIENT_ORDER_ID_INDEX = CLIENT_ORDER_ID_INDEX + DMAMsgSize.STRING_SIZE;
    private static final int ORDER_QUANTITY_INDEX = ORIGINAL_CLIENT_ORDER_ID_INDEX + DMAMsgSize.STRING_SIZE;
    private static final int PRICE_INDEX = ORDER_QUANTITY_INDEX + DMAMsgSize.INT_SIZE;
    public static final int MESSAGE_SIZE = PRICE_INDEX + DMAMsgSize.INT_SIZE;

    private String clientOrderId;
    private String originalClientOrderId;
    private Integer orderQuantity;
    private Integer price;

    public DMAMsgBodyReplaceOrder()
    {
        super();
    }

    public DMAMsgBodyReplaceOrder(String clientOrderId, String originalClientOrderId, Integer orderQuantity, Integer price)
    {
        super();
        this.clientOrderId = clientOrderId;
        this.originalClientOrderId = originalClientOrderId;
        this.orderQuantity = orderQuantity;
        this.price = price;
    }

    @Override
    public byte[] encode() throws MessageCodecException
    {
        byte[] result = new byte[MESSAGE_SIZE];
        byte[] clientOrderIdByte = EncodeDecodeUtil.encodeString(clientOrderId);
        byte[] originalClientOrderIdByte = EncodeDecodeUtil.encodeString(originalClientOrderId);
        byte[] orderQuantityByte = EncodeDecodeUtil.encodeInt(orderQuantity);
        byte[] priceByte = EncodeDecodeUtil.encodeInt(price);

        System.arraycopy(clientOrderIdByte, 0, result, CLIENT_ORDER_ID_INDEX, clientOrderIdByte.length);
        System.arraycopy(originalClientOrderIdByte, 0, result, ORIGINAL_CLIENT_ORDER_ID_INDEX, originalClientOrderIdByte.length);
        System.arraycopy(orderQuantityByte, 0, result, ORDER_QUANTITY_INDEX, orderQuantityByte.length);
        System.arraycopy(priceByte, 0, result, PRICE_INDEX, priceByte.length);

        return result;
    }

    @Override
    public void decode(byte[] input) throws MessageCodecException
    {
        byte[] clientOrderIdByte = new byte[DMAMsgSize.STRING_SIZE];
        byte[] originalClientOrderIdByte = new byte[DMAMsgSize.STRING_SIZE];
        byte[] orderQuantityByte = new byte[DMAMsgSize.INT_SIZE];
        byte[] priceByte = new byte[DMAMsgSize.INT_SIZE];

        System.arraycopy(input, CLIENT_ORDER_ID_INDEX, clientOrderIdByte, 0, clientOrderIdByte.length);
        System.arraycopy(input, ORIGINAL_CLIENT_ORDER_ID_INDEX, originalClientOrderIdByte, 0, originalClientOrderIdByte.length);
        System.arraycopy(input, ORDER_QUANTITY_INDEX, orderQuantityByte, 0, orderQuantityByte.length);
        System.arraycopy(input, PRICE_INDEX, priceByte, 0, priceByte.length);

        clientOrderId = EncodeDecodeUtil.decodeString(clientOrderIdByte);
        originalClientOrderId = EncodeDecodeUtil.decodeString(originalClientOrderIdByte);
        orderQuantity = EncodeDecodeUtil.decodeInt(orderQuantityByte);
        price = EncodeDecodeUtil.decodeInt(priceByte);
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

    @Override
    public String toString()
    {
        return "DMAMsgReplaceOrder [clientOrderId=" + clientOrderId + ", originalClientOrderId=" + originalClientOrderId + ", orderQuantity=" + orderQuantity + ", price=" + price + "]";
    }

    @Override
    public Object clone()
    {
        return new DMAMsgBodyReplaceOrder(clientOrderId, originalClientOrderId, orderQuantity, price);
    }

}
