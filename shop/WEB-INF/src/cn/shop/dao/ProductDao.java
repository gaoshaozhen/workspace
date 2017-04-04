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
    public Object getProduct(Map<?, ?> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        List<?> list = session.selectList("productMapper.getProduct", param);
        session.close();
        return list;
    }

    /**
     * 获得产品列表
     * 
     * @return
     */
    public Object updateProduct(Map<?, ?> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        session.update("productMapper.updateProduct", param);
        session.close();
        return true;
    }


    /**
     * 新增产品
     */
    public boolean addProduct(Map<?, ?> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        List<?> list = session.selectList("productMapper.addProduct", param);
        session.close();
        return true;
    }

    /**
     * 删除产品
     */
    public boolean deleteProduct(Map<?, ?> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        session.delete("productMapper.deleteProduct", param);
        session.close();
        return true;
    }
}
