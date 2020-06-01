package simulator.connectivity;

public class ConnectionState
{
    private boolean isConnected;

    public void setDisconnected()
    {
        isConnected = false;
    };

    public void setConnected()
    {
        isConnected = true;
    }

    public boolean isConnected()
    {
        return isConnected;
    }
}
