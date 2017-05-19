package cn.shop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;


public class UserDao
{
    SqlSessionFactory sqlSessionFactory;

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory)
    {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public SqlSessionFactory getSqlSessionFactory()
    {
        return this.sqlSessionFactory;
    }

    /**
     * 获得用户列表
     * 
     * @return
     */
    public Object getUser(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        List<Object> list;
        list = session.selectList("userMapper.getUser", param);
        session.close();
        return list;
    }

    public List<Map<String, Object>> getAllUser(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        List<Map<String, Object>> list = session.selectList("userMapper.getAllUser", param);
        session.close();
        return list;
    }
    
    /**
     * 新增用户
     */
    public void addUser(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        session.insert("userMapper.insertUser", param);
        session.close();
    }

    /**
     * 删除用户
     */
    public Object deleteUser()
    {
        return null;
    }

    public void updateUser(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        session.insert("userMapper.updateUser", param);
        session.close();
    }
}
