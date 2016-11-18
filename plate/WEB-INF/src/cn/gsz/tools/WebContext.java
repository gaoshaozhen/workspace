package cn.gsz.tools;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class WebContext
{
    private static ApplicationContext context;
    private static String beanUrl = "../conf/applicationContext.xml";
    private static String contextFileUrl = "WEB-INF/conf/applicationContext.xml";
    private static Logger logger = Logger.getLogger(WebContext.class);

    public static ApplicationContext getApplicationContext()
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

    public ApplicationContext getApplicatonContext()
    {
        ApplicationContext context;

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
        return context;
    }

    public static Object getBean(String beanId)
    {
        return getApplicationContext().getBean(beanId);
    }

    public static <T> T getBean(String beanId, Class<T> t)
    {
        return getApplicationContext().getBean(beanId, t);
    }
}
