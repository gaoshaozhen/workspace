package cn.shop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;


public class CartDao
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
     * 获得购物车列表
     * 
     * @return
     */
    public List<Map<String, Object>> getCartBySessionId(
            Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        List<Map<String, Object>> list;

        list = session.selectList("cartMapper.getCartBySessionId", param);
        session.close();        
        return list;
    }

    /**
     * 获得购物车列表
     * 
     * @return
     */
    public List<Map<String, Object>> getCartByCartId(
            Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        List<Map<String, Object>> list;

        list = session.selectList("cartMapper.getCartByCartId", param);
        session.close();        
        return list;
    }
    
    /**
     * 新增购物车
     */
    public void addCart(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        session.insert("cartMapper.addCart", param);
        session.close();
    }
    /**
     * 删除购物车
     */
    public void deleteCartByCartId(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        session.insert("cartMapper.deleteCartByCartId", param);
        session.close();
    }
    
    
}
