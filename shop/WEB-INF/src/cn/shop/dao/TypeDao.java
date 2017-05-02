package cn.shop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * 类型查询。
 * 
 * @author shaozhen
 */
public class TypeDao
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
     * 获得类型列表
     * 
     * @return
     */
    public Object getType(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        List list = session.selectList("typeMapper.getAllType", param);
        session.close();
        return list;
    }
    
    /**
     * 获得类型列表
     * 
     * @return
     */
    public Map<String, Object> getTypeByTypeId(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        Map<String, Object> map= session.selectOne("typeMapper.getTypeByTypeId", param);
        session.close();
        return map;
    }

    
    public Object getCount(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        Object object = session.selectOne("typeMapper.getCount", param);
        session.close();
        return object;
    }
    /**
     * 新增类型
     */
    public void addType(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        session.insert("typeMapper.insertType", param);
        session.close();
    }

    /**
     * 删除类型
     */
    public void deleteType(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        session.delete("typeMapper.deleteType", param);
        session.close();
    }
}
