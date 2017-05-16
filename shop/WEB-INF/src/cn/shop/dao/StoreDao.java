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
    public List<Map<String, Object>> getAllWarn()
    {
        SqlSession session = sqlSessionFactory.openSession();
        List<Map<String, Object>> list;

        list = session.selectList("storeMapper.getAllWarn");
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

        list = session.selectList("storeMapper.getWarn", param);
        session.close();
        return list;
    }

    public void addWarn(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();

        session.insert("storeMapper.addWarn", param);
        session.close();
    }

    public void updateWarn(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();

        session.insert("storeMapper.updateWarn", param);
        session.close();
    }
}
