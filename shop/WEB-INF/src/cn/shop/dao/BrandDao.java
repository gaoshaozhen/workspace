package cn.shop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * 品牌查询。
 * 
 * @author shaozhen
 */
public class BrandDao
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
     * 获得品牌列表
     * 
     * @return
     */
    public Object getBrand(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        List list = session.selectList("brandMapper.getBrand", param);
        session.close();
        return list;
    }

    /**
     * 新增品牌
     */
    public void addBrand(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        session.insert("brandMapper.insertBrand", param);
        session.close();
    }

    /**
     * 删除品牌
     */
    public void deleteBrand(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        session.delete("brandMapper.deleteBrand", param);
        session.close();
    }
    
    public void updateBrand(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        session.delete("brandMapper.updateBrand", param);
        session.close();
    }
}
