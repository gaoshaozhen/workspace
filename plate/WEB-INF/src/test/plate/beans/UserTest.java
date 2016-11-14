package test.plate.beans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class UserTest
{

    public static void main(String[] args)
    {
        ApplicationContext context = new FileSystemXmlApplicationContext(
                "WEB-INF/conf/beans.xml");// 通过ClassPathXmlApplicationContext获取beans.xml信息-->app

    }

}
