package test.plate.dao;

import org.hibernate.SessionFactory;

import cn.gsz.tools.ContextTool;
import cn.plate.javaBean.UserBean;

public class ServiceTest
{
    public static void main(String[] args)
    {
        SessionFactory sessionFactory = (SessionFactory) ContextTool
                .getBean("sessionFactory");

        UserBean userBean = new UserBean();
        userBean.setUserId("123");
        userBean.setPassword("123");
        userBean.setUsername("1234");
    }
}
