package test.plate.dao;

import org.springframework.context.ApplicationContext;

import cn.plate.javaBean.BeansContainer;
import cn.plate.javaBean.UserBean;
import cn.plate.service.dao.Dao;

public class ServiceTest
{
    public static void main(String[] args)
    {
        ApplicationContext ac = BeansContainer.getApplicationContext();
        // String sql = "select * from user";
        // SessionFactory sessionFactory = (SessionFactory) ac
        // .getBean("sessionFactory");
        Dao dao = Dao.getInstance();
        UserBean userBean = new UserBean();
        userBean.setUserId("123");
        userBean.setPassword("123");
        userBean.setUsername("1234");
        // dao.addUser(userBean);

        // System.out.println(dao.isUser(userBean));

    }
}
