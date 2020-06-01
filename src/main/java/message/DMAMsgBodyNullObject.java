package message;

public class DMAMsgBodyNullObject extends DMAMsgBody
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
        return "DMAMsgNullObject []";
    }

    @Override
    public int size()
    {
        return MESSAGE_SIZE;
    }

    @Override
    public Object clone()
    {
        return new DMAMsgBodyNullObject();
    }

}
