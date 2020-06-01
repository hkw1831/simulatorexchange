package util;

import exception.MessageCodecException;
import message.DMAMsgSize;

public class EncodeDecodeUtil
{
    public static byte[] encodeInt(int value) throws MessageCodecException
    {
        if (value > 65535)
        {
            throw new MessageCodecException("int " + value + " overflow");
        }
        byte[] b = new byte[]{
                (byte) (value >>> 8),
                (byte) value};
        return b;
    }

    public static int decodeInt(byte[] b) throws MessageCodecException
    {
        return (Byte.toUnsignedInt(b[0]) << 8) + Byte.toUnsignedInt(b[1]);
    }

    public static byte[] encodeString(String s) throws MessageCodecException
    {
        byte[] fixLengthByte = new byte[DMAMsgSize.STRING_SIZE];
        if (s != null)
        {
            byte[] sb = s.getBytes();
            if (sb.length > fixLengthByte.length)
            {
                throw new MessageCodecException("String field \"" + s + "\" too long");
            }
            for (int i = 0; i < sb.length; i++)
            {
                fixLengthByte[i] = sb[i];
            }
        }
        return fixLengthByte;
    }

    public static String decodeString(byte[] arr) throws MessageCodecException
    {
        int endOfStringIndex = findEndOfStringIndex(arr);
        byte[] resultByte = new byte[endOfStringIndex];
        System.arraycopy(arr, 0, resultByte, 0, endOfStringIndex);
        return new String(resultByte);
    }

    private static int findEndOfStringIndex(byte[] arr)
    {
        for (int index = 0; index < arr.length; index++)
        {
            if (arr[index] == 0)
            {
                return index;
            }
        }
        return arr.length;
    }

}