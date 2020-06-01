package message;

public class DMAMsgBodyLogout extends DMAMsgBody
{
    public static final int MESSAGE_SIZE = 0;

    @Override
    public byte[] encode()
    {
        return new byte[0];
    }

    @Override
    public void decode(byte[] input)
    {
    }

    @Override
    public String toString()
    {
        return "DMAMsgLogout []";
    }

    @Override
    public int size()
    {
        return MESSAGE_SIZE;
    }

    @Override
    public Object clone()
    {
        return new DMAMsgBodyLogout();
    }

}
