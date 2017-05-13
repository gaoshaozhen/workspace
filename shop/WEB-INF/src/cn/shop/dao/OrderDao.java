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
    public List<Map<String, Object>> getAllOrderByParamAndMemberId(Map<?, ?> dbParam)
    {
        SqlSession session = sqlSessionFactory.openSession();
        List<Map<String, Object>> list = session.selectList(
                "orderMapper.getAllOrderByParamAndMemberId",
                dbParam);
        session.close();
        return list;
    }
    
    public List<Map<String, Object>> getAllOrderByParam(Map<String, Object> dbParam)
    {
        SqlSession session = sqlSessionFactory.openSession();
        List<Map<String, Object>> list = session.selectList(
                "orderMapper.getAllOrderByParam",
                dbParam);
        session.close();
        return list;
    }
    public Long getTotalOfAllOrderByParam(Map<String, Object> dbParam)
    {
        Long total = 0L;
        SqlSession session = sqlSessionFactory.openSession();
        Map<String, Object> map = session.selectOne(
                "orderMapper.getTotalOfAllOrderByParam",
                dbParam);
        session.close();
        total = (Long)map.get("total");
        return total;
    }
    /**
     * 按照订单状态获得订单列表。
     * 
     * @return
     */
    public List<Map<String, Object>> getOrderListByStatus(Map<?, ?> dbParam)
    {
        SqlSession session = sqlSessionFactory.openSession();
        List<Map<String, Object>> list = session.selectList(
                "orderMapper.getOrderListByStatus", dbParam);
        session.close();
        return list;
    }

    /**
     * 获得单个订单详细信息。
     * 
     * @return
     */
    public Map<String, Object> getOneOrderById(Map<String, Object> dbParam)
    {
        SqlSession session = sqlSessionFactory.openSession();
        Map<String, Object> map = session.selectOne(
                "orderMapper.getOneOrderById", dbParam);

        session.close();
        return map;
    }

    /**
     * 新增订单
     */
    public boolean addOrder(Map<String, Object> dbParam)
    {
        SqlSession session = sqlSessionFactory.openSession();
        session.insert("orderMapper.addOrder", dbParam);
        session.close();
        return true;
    }

    /**
     * 删除订单
     */
    public boolean deleteOrder(Map<?, ?> dbParam)
    {
        SqlSession session = sqlSessionFactory.openSession();
        session.insert("orderMapper.deleteOrderById", dbParam);
        session.close();
        return true;
    }
    
    /**
     * 获取超时订单
     */
    public List<Map<String, Object>> getTomeoutOrder(Map<String, Object> param)
    {
        List<Map<String, Object>> list;
        SqlSession session = sqlSessionFactory.openSession();
        list = session.selectList("orderMapper.getTimeoutOrder", param);
        session.close();
        
        return list;
    }
    
    public void updateOrdersStatus(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        session.update("orderMapper.updateOrdersById", param);
        session.close();
    }
    
    public void updateOrderStatus(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        session.update("orderMapper.updateOrderById", param);
        session.close();
    }
    
    public void updatePayStatusByOrderId(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        session.update("orderMapper.updatePayStatusByOrderId", param);
        session.close();
    }
    
    /**
     * 添加订单日志。
     * @param param
     */
    public void addOrderLog(Map<String, Object>param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        session.update("orderMapper.addOrderLog", param);
        session.close();
    }
        
    /**
     * 获取订单日志。
     * @param param
     * @return
     */
    public List<Map<String, Object>>getOrderLog(Map<String, Object>param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        List<Map<String, Object>> list = 
                session.selectList("orderMapper.getOrderLog", param);
        return list;
    }
}
