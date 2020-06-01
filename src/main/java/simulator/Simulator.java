package simulator;

import simulator.connectivity.ClientThread;
import util.SimulatorLogger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Simulator
{
    private static final int PORT = 8888;
    private SimulatorLogger logger;
    private ServerSocket welcomeSocket;

    public static void main(String argv[]) throws Exception
    {
        Simulator simulator = new Simulator();
        simulator.startServer();
    }

    public Simulator() throws IOException
    {
        logger = new SimulatorLogger();
        welcomeSocket = new ServerSocket(PORT);
    }

    public void startServer() throws IOException
    {
        try
        {
            while (true)
            {
                Socket connectionSocket = welcomeSocket.accept();
                ClientThread t = new ClientThread(connectionSocket, logger);
                t.start();
            }
        }
        finally
        {
        }
    }

}

