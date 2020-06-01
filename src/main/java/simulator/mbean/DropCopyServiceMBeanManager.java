package simulator.mbean;

import exception.SimulatorException;
import simulator.connectivity.MessageSenderService;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class DropCopyServiceMBeanManager
{
    private static final String MBEAN_TYPE = ":type=";
    private static final String DROP_COPY_SERVICE_NAME = DropCopyService.class.getSimpleName();
    private static final String DROP_COPY_SERVICE_PACKAGE_NAME = DropCopyService.class.getPackage().getName();

    private ObjectName name;
    private DropCopyService mBean;
    private MBeanServer mbs;

    public DropCopyServiceMBeanManager(MessageSenderService senderService) throws SimulatorException
    {
        try
        {
            mBean = new DropCopyService(senderService);
            name = new ObjectName(DROP_COPY_SERVICE_PACKAGE_NAME + MBEAN_TYPE + DROP_COPY_SERVICE_NAME);
            mbs = ManagementFactory.getPlatformMBeanServer();
        }
        catch (Exception e)
        {
            throw new SimulatorException(e);
        }
    }

    public void register() throws SimulatorException
    {
        try
        {
            mbs.registerMBean(mBean, name);
        }
        catch (Exception e)
        {
            throw new SimulatorException(e);
        }
    }

    public void unregister() throws SimulatorException
    {
        try
        {
            mbs.unregisterMBean(name);
        }
        catch (Exception e)
        {
            throw new SimulatorException(e);
        }
    }

}
