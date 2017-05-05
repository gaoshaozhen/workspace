package cn.shop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class TagDao
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
     * 
     * @return
     */
    public List<Map<String, Object>> getAllTag()
    {
        SqlSession session = sqlSessionFactory.openSession();
        List<Map<String, Object>> list;
                                   
        list = session.selectList("tagMapper.getAllTag");
        session.close();
        return list;
    }
}
