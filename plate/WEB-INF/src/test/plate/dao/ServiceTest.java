package test.plate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;

import cn.gsz.tools.ContextTool;
import cn.plate.javaBean.BeansContainer;
import cn.plate.javaBean.UserBean;

public class ServiceTest
{
    public static void main(String[] args)
    {
        ApplicationContext ac = BeansContainer.getApplicationContext();
        String sql = "select * from user";
        SessionFactory sessionFactory = (SessionFactory) ContextTool
                .getBean("sessionFactory");
        // Dao dao = Dao.getInstance();
        UserBean userBean = new UserBean();
        userBean.setUserId("123");
        userBean.setPassword("123");
        userBean.setUsername("1234");
        // dao.addUser(userBean);
        Session session = sessionFactory.openSession();

        // System.out.println(dao.isUser(userBean));

    }
}
