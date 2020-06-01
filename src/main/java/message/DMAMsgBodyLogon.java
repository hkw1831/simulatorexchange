package message;

import exception.MessageCodecException;
import util.EncodeDecodeUtil;

public class DMAMsgBodyLogon extends DMAMsgBody
{
    private static final int USERNAME_INDEX = 0;
    private static final int PASSWORD_INDEX = USERNAME_INDEX + DMAMsgSize.STRING_SIZE;
    private static final int NEXT_SEQUENCE_NUMBER_INDEX = PASSWORD_INDEX + DMAMsgSize.STRING_SIZE;
    public static final int MESSAGE_SIZE = NEXT_SEQUENCE_NUMBER_INDEX + DMAMsgSize.INT_SIZE;

    private String username;
    private String password;
    private int nextSequenceNumber;

    public DMAMsgBodyLogon()
    {

    }

    public DMAMsgBodyLogon(String username, String password, int nextSequenceNumber)
    {
        this.username = username;
        this.password = password;
        this.nextSequenceNumber = nextSequenceNumber;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public int getNextSequenceNumber()
    {
        return nextSequenceNumber;
    }

    public void setNextSequenceNumber(int nextSequenceNumber)
    {
        this.nextSequenceNumber = nextSequenceNumber;
    }

    @Override
    public byte[] encode() throws MessageCodecException
    {
        byte[] result = new byte[MESSAGE_SIZE];
        byte[] usernameByte = EncodeDecodeUtil.encodeString(username);
        byte[] passwordByte = EncodeDecodeUtil.encodeString(password);
        byte[] nextSequenceNumberByte = EncodeDecodeUtil.encodeInt(nextSequenceNumber);

        System.arraycopy(usernameByte, 0, result, USERNAME_INDEX, usernameByte.length);
        System.arraycopy(passwordByte, 0, result, PASSWORD_INDEX, passwordByte.length);
        System.arraycopy(nextSequenceNumberByte, 0, result, NEXT_SEQUENCE_NUMBER_INDEX, nextSequenceNumberByte.length);

        return result;
    }

    @Override
    public void decode(byte[] input) throws MessageCodecException
    {
        byte[] usernameByte = new byte[DMAMsgSize.STRING_SIZE];
        byte[] passwordByte = new byte[DMAMsgSize.STRING_SIZE];
        byte[] nextSequenceNumberByte = new byte[DMAMsgSize.INT_SIZE];

        System.arraycopy(input, USERNAME_INDEX, usernameByte, 0, usernameByte.length);
        System.arraycopy(input, PASSWORD_INDEX, passwordByte, 0, passwordByte.length);
        System.arraycopy(input, NEXT_SEQUENCE_NUMBER_INDEX, nextSequenceNumberByte, 0, nextSequenceNumberByte.length);

        username = EncodeDecodeUtil.decodeString(usernameByte);
        password = EncodeDecodeUtil.decodeString(passwordByte);
        nextSequenceNumber = EncodeDecodeUtil.decodeInt(nextSequenceNumberByte);
    }

    @Override
    public String toString()
    {
        return "DMAMsgLogon [username=" + username + ", password=" + password + ", nextSequenceNumber=" + nextSequenceNumber + "]";
    }

    @Override
    public int size()
    {
        return MESSAGE_SIZE;
    }

    @Override
    public Object clone()
    {
        return new DMAMsgBodyLogon(username, password, nextSequenceNumber);
    }

}
