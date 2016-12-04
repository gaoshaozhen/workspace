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
    SqlSessionFactory sqlSessionFactory;

    public UserDao(SqlSessionFactory sqlSessionFactory)
    {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public int addUser(UserModel userModel)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    public int update(UserModel userModel)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    public int delete(UserModel userModel)
    {
        // TODO Auto-generated method stub
        return 0;
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

    private void close(SqlSession session)
    {
        if (session != null)
        {
            session.close();
        }
    }

    public List<Map> getAllUser()
    {
        // TODO Auto-generated method stub
        List<Map> userList = null;
        SqlSession session = sqlSessionFactory.openSession();
        userList = session.selectList("userMapper.selectAllUser");
        close(session);
        log.info("查询完成");
        return userList;
    }
}
