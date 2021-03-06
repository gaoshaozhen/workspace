package cn.gsz.tools;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class ContextTool
{
    private static ApplicationContext context = null;
    private static String beanUrl = "../conf/applicationContext.xml";
    private static String contextFileUrl = "WEB-INF/conf/applicationContext.xml";
    private static Logger logger = Logger.getLogger(ContextTool.class);
    static
    {

        init();

    }

    private static void init()
    {
        try
        {
            context = new ClassPathXmlApplicationContext(beanUrl);
        }
        catch (Exception e)
        {
            logger.error(e.getLocalizedMessage());
            logger.info("更换加载方式");
            context = new FileSystemXmlApplicationContext(contextFileUrl);
        }

    }

    public static Object getBean(String beanId)
    {
        if (context == null)
        {
            return null;
        }
        return context.getBean(beanId);
    }

    public static <T> T getBean(String beanId, Class<T> c)
    {
        return context.getBean(beanId, c);
    }

    public ApplicationContext getApplicationContext()
    {
        if (context == null)
        {
            synchronized (ContextTool.class)
            {
                if (context == null)
                {
                    try
                    {
                        context = new ClassPathXmlApplicationContext(beanUrl);
                    }
                    catch (Exception e)
                    {
                        logger.error(e.getLocalizedMessage());
                        logger.info("更换加载方式");
                        context = new FileSystemXmlApplicationContext(
                                contextFileUrl);
                    }
                }
            }
        }
        return context;
    }
}
