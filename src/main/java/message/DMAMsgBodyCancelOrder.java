package message;

import exception.MessageCodecException;
import util.EncodeDecodeUtil;

public class DMAMsgBodyCancelOrder extends DMAMsgBody
{
    private static final int CLIENT_ORDER_ID_INDEX = 0;
    private static final int ORIGINAL_CLIENT_ORDER_ID_INDEX = CLIENT_ORDER_ID_INDEX + DMAMsgSize.STRING_SIZE;
    public static final int MESSAGE_SIZE = ORIGINAL_CLIENT_ORDER_ID_INDEX + DMAMsgSize.STRING_SIZE;

    private String clientOrderId;
    private String originalClientOrderId;

    public DMAMsgBodyCancelOrder()
    {
    }

    public DMAMsgBodyCancelOrder(String clientOrderId, String originalClientOrderId)
    {
        this.clientOrderId = clientOrderId;
        this.originalClientOrderId = originalClientOrderId;
    }

    @Override
    public byte[] encode() throws MessageCodecException
    {
        byte[] result = new byte[MESSAGE_SIZE];
        byte[] clientOrderIdByte = EncodeDecodeUtil.encodeString(clientOrderId);
        byte[] originalClientOrderIdByte = EncodeDecodeUtil.encodeString(originalClientOrderId);

        System.arraycopy(clientOrderIdByte, 0, result, CLIENT_ORDER_ID_INDEX, clientOrderIdByte.length);
        System.arraycopy(originalClientOrderIdByte, 0, result, ORIGINAL_CLIENT_ORDER_ID_INDEX, originalClientOrderIdByte.length);

        return result;
    }

    @Override
    public void decode(byte[] input) throws MessageCodecException
    {
        byte[] clientOrderIdByte = new byte[DMAMsgSize.STRING_SIZE];
        byte[] originalClientOrderIdByte = new byte[DMAMsgSize.STRING_SIZE];

        System.arraycopy(input, CLIENT_ORDER_ID_INDEX, clientOrderIdByte, 0, clientOrderIdByte.length);
        System.arraycopy(input, ORIGINAL_CLIENT_ORDER_ID_INDEX, originalClientOrderIdByte, 0, originalClientOrderIdByte.length);

        clientOrderId = EncodeDecodeUtil.decodeString(clientOrderIdByte);
        originalClientOrderId = EncodeDecodeUtil.decodeString(originalClientOrderIdByte);
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

    @Override
    public String toString()
    {
        return "DMAMsgCancelOrder [clientOrderId=" + clientOrderId + ", originalClientOrderId=" + originalClientOrderId + "]";
    }

    @Override
    public Object clone()
    {
        return new DMAMsgBodyCancelOrder(clientOrderId, originalClientOrderId);
    }

}
