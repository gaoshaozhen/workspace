package cn.plate.javaBean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class BeansContainer
{
    static ApplicationContext app;
    static String beansPath = "WEB-INF/conf/beans.xml";
    static
    {
        app = new FileSystemXmlApplicationContext(beansPath);
    }

    public static ApplicationContext getApplicationContext()
    {
        return app;
    }
}
