package simulator.mbean;

import exception.MessageCodecException;

import java.io.IOException;

public interface DropCopyServiceMBean
{
    void sendSampleDropCopy() throws IOException, MessageCodecException, IOException, MessageCodecException;

    void sendDropCopy(String side, Integer orderQuantity, Integer price, String orderType) throws IOException, MessageCodecException;
}
