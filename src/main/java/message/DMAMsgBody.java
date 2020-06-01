package message;

import exception.MessageCodecException;

public abstract class DMAMsgBody
{
    public abstract byte[] encode() throws MessageCodecException;

    public abstract void decode(byte[] input) throws MessageCodecException;

    public abstract int size();

    @Override
    public abstract Object clone();
}

