package cn.shop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class OrderItemsDao
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
     * 
     * @return
     */
    public List<Map<String, Object>> addOrderItems(
            Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        List<Map<String, Object>> list;

        list = session.selectList("orderItemsMapper.addOrderItems", param);
        session.close();
        return list;
    }
}
