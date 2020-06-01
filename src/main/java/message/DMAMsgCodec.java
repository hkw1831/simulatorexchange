//package message;
//
//import exception.MessageCodecException;
//import util.EncodeDecodeUtil;
//
//public class DMAMsgCodec implements GenericMessageCodec<SocketData, DMAMsg, SocketDataInterface, DMAMsg>
//{
//
//    @Override
//    public SocketDataInterface encode(DMAMsg data) throws MessageCodecException
//    {
//        byte[] result = data.encode();
//        return new SocketData(result);
//    }
//
//    @Override
//    public DMAMsg decode(SocketData data) throws MessageCodecException
//    {
//        byte[] messageData = data.getBytes();
//
//        if (!data.ensureCapacity(DMAMsgSize.INT_SIZE))
//        {
//            return null;
//        }
//
//        int size = getMessageSize(data, messageData);
//        if (!data.ensureCapacity(size))
//        {
//            return null;
//        }
//        return extractMessage(data, messageData, size);
//    }
//
//    public DMAMsg extractMessage(SocketData data, byte[] messageData, int size) throws MessageCodecException
//    {
//        byte[] resultData = new byte[size];
//        System.arraycopy(messageData, data.getMark(), resultData, 0, size);
//        data.incrementMark(size);
//        data.ensureCapacity(data.getBufferSize());
//        DMAMsg msg = new DMAMsg();
//        msg.decode(resultData);
//        return msg;
//    }
//
//    public int getMessageSize(SocketData data, byte[] messageData) throws MessageCodecException
//    {
//        byte[] sizeData = new byte[]{messageData[data.getMark()], messageData[data.getMark() + 1]};
//        int size = EncodeDecodeUtil.decodeInt(sizeData);
//        return size;
//    }
//
//}
