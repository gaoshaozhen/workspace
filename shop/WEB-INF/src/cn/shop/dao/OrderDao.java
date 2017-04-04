package cn.shop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * 订单处理。
 * 
 * @author shaozhen
 */
public class OrderDao
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
     * 获得订单列表
     * 
     * @return
     */
    public Object getOrder(Map<?, ?> dbParam)
    {
        SqlSession session = sqlSessionFactory.openSession();
        List<?> list = session.selectList("orderMapper", dbParam);
        session.close();
        return list;
    }

    /**
     * 新增订单
     */
    public boolean addOrder(Map<String, ?> dbParam)
    {
        SqlSession session = sqlSessionFactory.openSession();
        session.insert("orderMapper.insertOrder", dbParam);
        session.close();
        return true;
    }

    /**
     * 删除订单
     */
    public boolean deleteOrder(Map<?, ?> dbParam)
    {
        SqlSession session = sqlSessionFactory.openSession();
        session.insert("orderMapper.insertOrder", dbParam);
        session.close();
        return true;
    }
}
