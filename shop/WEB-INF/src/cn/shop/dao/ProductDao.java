package cn.shop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * 产品查询。
 * 
 * @author shaozhen
 * @version 1.0 2017.4.2
 */
public class ProductDao
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
     * 获得产品列表
     * 
     * @return
     */
    public List<Map<String, Object>> getProduct(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        List<Map<String, Object>> list = session.selectList(
                "productMapper.getProductByGoodsId", param);

        session.close();
        return list;
    }

    /**
     * 获得产品列表
     * 
     * @return
     */
    public List<Map<String, Object>> getAllProduct(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        List<Map<String, Object>> list = session.selectList(
                "productMapper.getAllProduct", param);

        session.close();
        return list;
    }

    /**
     * 获得产品列表
     * 
     * @return
     */
    public long getAllProductTotal(Map<String, Object> param)
    {
        long total = 0;
        SqlSession session = sqlSessionFactory.openSession();
        Map<String, Object> map = session.selectOne(
                "productMapper.getAllProductTotal");

        session.close();
        total = (Long) map.get("total");
        return total;
    }

    /**
     * 获得产品列表
     * 
     * @return
     */
    public Map<String, Object> getOneProductByPruductId(
            Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        Map<String, Object> map = session.selectOne(
                "productMapper.getOneProductByProductId", param);

        session.close();
        return map;
    }

    /**
     * 获得产品列表
     * 
     * @return
     */
    // public Object updateProduct(Map<String, Object> param)
    // {
    // SqlSession session = sqlSessionFactory.openSession();
    // session.update("productMapper.updateProduct", param);
    // session.close();
    // return true;
    // }
    //
    //
    /**
     * 新增产品
     */
    public boolean addProduct(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        session.insert("productMapper.addProduct", param);
        session.close();
        return true;
    }

    /**
     * 获取产品
     */
    public Map<String, Object> getOneProductDetailBySn(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        Map<String, Object> map = session.selectOne(
                "productMapper.getOneProductDetailBySn", param);
        session.close();
        return map;
    }
    
    public void updateStore(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        session.update("productMapper.updateStore", param);        
        session.close();        
    }
}
