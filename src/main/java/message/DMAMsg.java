package message;

import exception.MessageCodecException;

public class DMAMsg
{

    private DMAMsgHeader header;
    private DMAMsgBody body;
    private volatile int currentIndex = 0;

    public DMAMsgHeader getHeader()
    {
        return header;
    }

    public void setHeader(DMAMsgHeader header)
    {
        this.header = header;
    }

    public DMAMsgBody getBody()
    {
        return body;
    }

    public void setBody(DMAMsgBody body)
    {
        this.body = body;
    }

    public byte[] encode() throws MessageCodecException
    {
        int messageSize = size();
        byte[] result = new byte[messageSize];

        header.setLength(messageSize);

        currentIndex = 0;

        byte[] encodedHeader = getHeader().encode();
        System.arraycopy(encodedHeader, 0, result, currentIndex, encodedHeader.length);
        currentIndex += encodedHeader.length;

        byte[] encodedBody = getBody().encode();
        System.arraycopy(encodedBody, 0, result, currentIndex, encodedBody.length);
        currentIndex += encodedBody.length;
        return result;
    }

    public void decode(byte[] input) throws MessageCodecException
    {
        currentIndex = 0;

        byte[] headerByte = new byte[DMAMsgHeader.MESSAGE_SIZE];
        System.arraycopy(input, currentIndex, headerByte, 0, headerByte.length);
        currentIndex += headerByte.length;
        DMAMsgHeader header = new DMAMsgHeader();
        header.decode(headerByte);
        setHeader(header);

        DMAMsgBody body = createEmptyBody(header);
        byte[] bodyByte = new byte[body.size()];
        System.arraycopy(input, currentIndex, bodyByte, 0, bodyByte.length);
        currentIndex += bodyByte.length;
        body.decode(bodyByte);
        setBody(body);
    }

    private DMAMsgBody createEmptyBody(DMAMsgHeader header) throws MessageCodecException
    {
        String msgType = header.getMessageType();
        return DMAMsgBodyFactory.createDMAMsgBody(msgType);
    }

    public int size()
    {
        return header.size() + body.size();
    }

    @Override
    public String toString()
    {
        return "DMAMsg [header=" + header + ", body=" + body + "]";
    }

    @Override
    public Object clone()
    {
        DMAMsg msg = new DMAMsg();
        msg.setHeader((DMAMsgHeader) (header.clone()));
        msg.setBody((DMAMsgBody) (body.clone()));
        return msg;
    }
}