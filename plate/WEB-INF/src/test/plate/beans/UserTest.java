package test.plate.beans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import cn.plate.javaBean.User;

public class UserTest
{

    public static void main(String[] args)
    {
        ApplicationContext app = new FileSystemXmlApplicationContext(
                "WEB-INF/conf/beans.xml");// 通过ClassPathXmlApplicationContext获取beans.xml信息-->app
        User user1 = (User) app.getBean("user");
        user1.setId("we");
        User user2 = (User) app.getBean("user");
        System.out.println(user2.getId());

        ApplicationContext app1 = new FileSystemXmlApplicationContext(
                "WEB-INF/conf/beans.xml");// 通过ClassPathXmlApplicationContext获取beans.xml信息-->app
        User user4 = (User) app1.getBean("user");
        System.out.println(user4.getId());

    }

}
