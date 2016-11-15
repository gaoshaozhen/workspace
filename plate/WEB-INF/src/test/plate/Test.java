package test.plate;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Test
{
    private static Logger logger = Logger.getLogger(Test.class);

    public static void main(String[] args)
    {
        PropertyConfigurator.configure("WEB-INF/conf/log4j.properties");
        try
        {
            Test t = Test.class.newInstance();
        }
        catch (InstantiationException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }

    }
}
