package cn.shop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * 规格查询。
 * 
 * @author shaozhen
 */
public class SpecificationDao
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
     * 获得规格列表
     * 
     * @return
     */
    public Object getSpecification(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        List list = session.selectList("specificationMapper.getSpecification",
                param);
        session.close();
        return list;
    }

    /**
     * 新增规格
     */
    public void addSpecification(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        session.insert("specificationMapper.insertSpecification", param);
        session.close();
    }

    /**
     * 删除规格
     */
    public void deleteSpecification(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        session.delete("specificationMapper.deleteSpecification", param);
        session.close();
    }

    /**
     * 更新规格
     */
    public void updateSpecification(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        session.delete("specificationMapper.updateSpecification", param);
        session.close();
    }
}
