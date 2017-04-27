package cn.shop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * 支付方式查询。
 * 
 * @author shaozhen
 */
public class PaymentDao
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
     * 获得支付方式列表
     * 
     * @return
     */
    public Object getPayment(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        List list = session.selectList("paymentMapper.getPayment", param);
        session.close();
        return list;
    }

    public Object getCount(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        Object object = session.selectOne("paymentMapper.getCount", param);
        session.close();
        return object;
    }
    
    /**
     * 新增支付方式
     */
    public void addPayment(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        session.insert("paymentMapper.insertPayment", param);
        session.close();
    }

    /**
     * 删除支付方式
     */
    public void deletePayment(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        session.delete("paymentMapper.deletePayment", param);
        session.close();
    }
}
