package message;

import exception.MessageCodecException;
import util.EncodeDecodeUtil;

public class DMAMsgHeader
{
    private static final int LENGTH_INDEX = 0;
    private static final int MESSAGE_TYPE_INDEX = LENGTH_INDEX + DMAMsgSize.INT_SIZE;
    private static final int SEQUENCE_NUMBER_INDEX = MESSAGE_TYPE_INDEX + DMAMsgSize.STRING_SIZE;
    private static final int USERNAME_INDEX = SEQUENCE_NUMBER_INDEX + DMAMsgSize.INT_SIZE;
    public static final int MESSAGE_SIZE = USERNAME_INDEX + DMAMsgSize.STRING_SIZE;

    private int length;
    private String messageType;
    private int sequenceNumber;
    private String username;

    public DMAMsgHeader()
    {

    }

    public DMAMsgHeader(int length, String messageType, int sequenceNumber, String username)
    {
        super();
        this.length = length;
        this.messageType = messageType;
        this.sequenceNumber = sequenceNumber;
        this.username = username;
    }

    public int getLength()
    {
        return length;
    }

    public void setLength(int length)
    {
        this.length = length;
    }

    public String getMessageType()
    {
        return messageType;
    }

    public void setMessageType(String messageType)
    {
        this.messageType = messageType;
    }

    public int getSequenceNumber()
    {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber)
    {
        this.sequenceNumber = sequenceNumber;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public byte[] encode() throws MessageCodecException
    {
        byte[] result = new byte[MESSAGE_SIZE];
        byte[] lengthByte = EncodeDecodeUtil.encodeInt(length);
        byte[] messageTypeByte = EncodeDecodeUtil.encodeString(messageType);
        byte[] sequenceNumberByte = EncodeDecodeUtil.encodeInt(sequenceNumber);
        byte[] usernameByte = EncodeDecodeUtil.encodeString(username);

        System.arraycopy(lengthByte, 0, result, LENGTH_INDEX, lengthByte.length);
        System.arraycopy(messageTypeByte, 0, result, MESSAGE_TYPE_INDEX, messageTypeByte.length);
        System.arraycopy(sequenceNumberByte, 0, result, SEQUENCE_NUMBER_INDEX, sequenceNumberByte.length);
        System.arraycopy(usernameByte, 0, result, USERNAME_INDEX, usernameByte.length);

        return result;
    }

    public void decode(byte[] input) throws MessageCodecException
    {
        byte[] lengthByte = new byte[DMAMsgSize.INT_SIZE];
        byte[] messageTypeByte = new byte[DMAMsgSize.STRING_SIZE];
        byte[] sequenceNumberByte = new byte[DMAMsgSize.INT_SIZE];
        byte[] usernameByte = new byte[DMAMsgSize.STRING_SIZE];

        System.arraycopy(input, LENGTH_INDEX, lengthByte, 0, lengthByte.length);
        System.arraycopy(input, MESSAGE_TYPE_INDEX, messageTypeByte, 0, messageTypeByte.length);
        System.arraycopy(input, SEQUENCE_NUMBER_INDEX, sequenceNumberByte, 0, sequenceNumberByte.length);
        System.arraycopy(input, USERNAME_INDEX, usernameByte, 0, usernameByte.length);

        length = EncodeDecodeUtil.decodeInt(lengthByte);
        messageType = EncodeDecodeUtil.decodeString(messageTypeByte);
        sequenceNumber = EncodeDecodeUtil.decodeInt(sequenceNumberByte);
        username = EncodeDecodeUtil.decodeString(usernameByte);
    }

    public int size()
    {
        return MESSAGE_SIZE;
    }

    @Override
    public String toString()
    {
        return "DMAMsgHeader [length=" + length + ", messageType=" + messageType + ", sequenceNumber=" + sequenceNumber + ", username=" + username + "]";
    }

    @Override
    public Object clone()
    {
        return new DMAMsgHeader(length, messageType, sequenceNumber, username);
    }
}
