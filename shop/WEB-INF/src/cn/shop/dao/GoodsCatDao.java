package cn.shop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class GoodsCatDao
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
     * 获得邮件列表
     * 
     * @return
     */
    public List<Map<String, Object>> getGoodsCat(Map<String, Object> dbParam)
    {
        SqlSession session = sqlSessionFactory.openSession();
        List<Map<String, Object>> list = session.selectList(
                "goodsCatMapper.getAllGoodsCatId", dbParam);
        session.close();
        return list;
    }
    
    /**
     * 获得邮件列表
     * 
     * @return
     */
    public List<Map<String, Object>> geAlltGoodsCat()
    {
        SqlSession session = sqlSessionFactory.openSession();
        List<Map<String, Object>> list = session.selectList(
                "goodsCatMapper.getAllGoodsCat");
        session.close();
        return list;
    }

    /**
     * 搜索包含指定若干个cat_id的记录
     * 
     * @param dbParam
     * @return
     */
    public List<Map<String, Object>> getGoodsCatByCatIds(
            Map<String, Object> dbParam)
    {
        SqlSession session = sqlSessionFactory.openSession();
        List<Map<String, Object>> list = session.selectList(
                "goodsCatMapper.getGoodsCatByCatIds", dbParam);
        session.close();
        return list;
    }

    /**
     * 搜索包含指定若干个parent_id的记录
     * 
     * @param dbParam
     * @return
     */
    public List<Map<String, Object>> getGoodsCatByParentIds(
            Map<String, Object> dbParam)
    {
        SqlSession session = sqlSessionFactory.openSession();
        List<Map<String, Object>> list = session.selectList(
                "goodsCatMapper.getGoodsCatByParentIds", dbParam);
        session.close();
        return list;
    }
    
    public Map<String, Object> getOneGoodsCatByCatId(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        Map<String, Object> map = session.selectOne("goodsCatMapper.getOneGoodsCatByCatId",param);
        return map;
    }
}
