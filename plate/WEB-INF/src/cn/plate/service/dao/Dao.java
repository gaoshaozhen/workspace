package cn.plate.service.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import cn.plate.javaBean.UserBean;
import cn.plate.service.entity.UserEntity;

public class Dao
{
    SessionFactory sessionFactory;
    static Logger logger = Logger.getLogger(Dao.class);

    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
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
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
            session.getTransaction().rollback();
            return false;// 添加失败
        } finally
        {
            closeSession(session);
        }
        return true;// 添加成功
    }

    // 检查是否是用户
    public boolean isUser(UserBean userBean)
    {
        Session session = null;
        String sql = String
                .format("select * from user where userId = \'%s\' and password = \'%s\' ;",
                        userBean.getUserId(), userBean.getPassword());
        try
        {
            session = sessionFactory.openSession();
            System.out.println(sql);
            Query query = session.createSQLQuery(sql);
            if (query.list().size() > 0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
            return false;// 添加失败
        } finally
        {
            closeSession(session);
        }
    }

    // 添加单个用户
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
        }
        catch (Exception e)
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

    public List<?> getAllUser()
    {
        List<?> list = null;
        Session session = null;
        String sql = "select * from user";

        try
        {
            session = sessionFactory.openSession();
            Query query = session.createSQLQuery(sql);
            list = query.list();
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
        } finally
        {
            closeSession(session);
        }
        return list;
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
