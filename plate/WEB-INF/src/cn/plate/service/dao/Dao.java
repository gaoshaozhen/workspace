package cn.plate.service.dao;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;

import cn.plate.javaBean.BeansContainer;
import cn.plate.javaBean.UserBean;
import cn.plate.service.entity.UserEntity;

public class Dao
{
    private SessionFactory sessionFactory;
    private Logger logger = Logger.getLogger(Dao.class);
    private static Dao dao = null;
    static Object lock = new Object();

    private Dao()
    {
        ApplicationContext ac = BeansContainer.getApplicationContext();
        sessionFactory = (SessionFactory) ac.getBean("sessionFactory");
    }

    public static Dao getInstance()
    {
        synchronized (lock)
        {
            if (dao == null)
            {
                dao = new Dao();
            }
            return dao;
        }
    }

    // 添加用户
    public boolean addUser(UserEntity user)
    {
        Session session = null;
        try
        {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            logger.info("用户：" + user.getUserId() + "添加成功");
        } catch (Exception e)
        {
            logger.error(e.getMessage());
            session.getTransaction().rollback();
            return false;// 添加失败
        } finally
        {
            if (session != null)
            {
                if (session.isOpen())
                {
                    // 关闭session
                    session.close();
                }
            }
        }
        return true;// 添加成功
    }

    // 获得用户
    public boolean isUser(UserBean userBean)
    {
        Session session = null;
        String sql = String.format(
                "select * from user where userId = %s and password = %s ;",
                userBean.getUserId(), userBean.getPassword());
        try
        {
            session = sessionFactory.openSession();
            System.out.println(sql);
            Query query = session.createSQLQuery(sql);
            if (query.list().size() > 0)
            {
                return true;
            } else
            {
                return false;
            }
        } catch (Exception e)
        {
            logger.error(e.getMessage());
            session.getTransaction().rollback();
            return false;// 添加失败
        } finally
        {
            closeSession(session);
        }
    }

    // 添加用户
    public boolean addUser(UserBean userBean)
    {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userBean.getUserId());
        userEntity.setPassword(userBean.getPassword());
        userEntity.setUsername(userBean.getUsername());
        Session session = null;

        try
        {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(userEntity);
            session.getTransaction().commit();
        } catch (Exception e)
        {
            logger.error(e.getMessage());
            // 回滚事务
            session.getTransaction().rollback();
            return false;
        } finally
        {
            closeSession(session);
        }
        return true;
    }

    private void closeSession(Session session)
    {
        if (session != null)
        {
            if (session.isOpen())
            {
                // 关闭session
                session.close();
            }
        }
    }
}
