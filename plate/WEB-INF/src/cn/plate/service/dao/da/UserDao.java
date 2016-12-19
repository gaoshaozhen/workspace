package cn.plate.service.dao.da;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import cn.plate.service.dao.UserDaoInterface;
import cn.plate.service.model.UserModel;

public class UserDao implements UserDaoInterface
{
    Logger log = Logger.getLogger(UserDao.class);
    SqlSessionFactory sqlSessionFactory = null;

    public UserDao(SqlSessionFactory sqlSessionFactory)
    {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    /**
     * 返回1表示添加成功，返回0表示插入意外失败
     */
    public int addUser(UserModel userModel)
    {
        int r = 0;
        SqlSession session = null;

        try
        {
            session = sqlSessionFactory.openSession();
            r = session.insert("userMapper.addUser", userModel);
            session.toString();
            session.commit();
        }
        catch (Exception e)
        {
            session.rollback();

            log.error(e.getLocalizedMessage());
        } finally
        {
            close(session);
        }
        log.debug("r=" + r);
        return r;
    }

    public int update(UserModel userModel)
    {
        // TODO Auto-generated method stub
        SqlSession session = sqlSessionFactory.openSession();
        int r = session.update("userMapper.updateUser", userModel);
        close(session);
        return r;
    }

    public int delete(UserModel userModel)
    {
        // TODO Auto-generated method stub
        SqlSession session = sqlSessionFactory.openSession();
        int r = session.selectOne("userMapper.deleteUser", userModel);
        close(session);
        return r;
    }

    public UserModel getUser(UserModel userModel)
    {
        UserModel user = null;
        SqlSession session = sqlSessionFactory.openSession();
        user = session.selectOne("userMapper.getOneUser", userModel);
        close(session);
        log.info("查询完成");
        return user;
    }

    public List<Map<String, Object>> getAllUser()
    {
        // TODO Auto-generated method stub
        List<Map<String, Object>> userList = null;
        SqlSession session = sqlSessionFactory.openSession();
        userList = session.selectList("userMapper.selectAllUser");
        close(session);
        log.info("查询完成");
        return userList;
    }

    // 关闭session
    private void close(SqlSession session)
    {
        if (session != null)
        {
            session.close();
        }
    }
}
