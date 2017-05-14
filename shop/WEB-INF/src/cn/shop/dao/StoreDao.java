package cn.shop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class StoreDao
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
     * 获得收货地址列表
     * 
     * @return
     */
    public List<Map<String, Object>> getAllStore()
    {
        SqlSession session = sqlSessionFactory.openSession();
        List<Map<String, Object>> list;

        list = session.selectList("storeMapper.getAllStore");
        session.close();
        return list;
    }

    /**
     * 获得收货地址列表
     * 
     * @return
     */
    public List<Map<String, Object>> getStore(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        List<Map<String, Object>> list;

        list = session.selectList("storeMapper.getStore", param);
        session.close();
        return list;
    }

    public void addStore(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();

        session.insert("storeMapper.addStore", param);
        session.close();
    }

    public void updateStore(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();

        session.insert("storeMapper.updateStore", param);
        session.close();
    }
}
