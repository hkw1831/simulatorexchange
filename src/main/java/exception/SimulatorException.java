package exception;

public class SimulatorException extends Exception
{

    private static final long serialVersionUID = -6251942326727742589L;

    public SimulatorException(Exception e)
    {
        super(e);
    }

}
