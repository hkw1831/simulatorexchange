package simulator.handler;

import exception.SimulatorException;
import message.DMAMsg;

public interface MessageHandler
{
    void handle(DMAMsg msg) throws SimulatorException;
}
