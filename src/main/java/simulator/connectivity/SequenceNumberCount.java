package simulator.connectivity;

import java.util.concurrent.atomic.AtomicInteger;

public class SequenceNumberCount
{
    private AtomicInteger count;

    public SequenceNumberCount()
    {
        count = new AtomicInteger();
    }

    public int nextSequenceNumber()
    {
        return count.incrementAndGet();
    }

}
