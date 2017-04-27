package cn.shop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class GoodsDao
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
     * 获得商品列表
     * 
     * @return
     */
    public List<Map<String, Object>> getGoods(Map<String, Object> dbParam)
    {
        SqlSession session = sqlSessionFactory.openSession();
        List<Map<String, Object>> list = session.selectList(
                "goodsMapper.getAllGoodsId", dbParam);
        session.close();
        return list;
    }

    public int getAllTotal()
    {
        return 0;
    }

    public int getGoodsTotalByTypeIds(Map<String, Object> dbParam)
    {
        return 0;
    }

    /**
     * 获得商品列表，分页查询。
     * 
     * @return
     */
    public List<Map<String, Object>> getAllGoods(Map<String, Object> dbParam)
    {
        SqlSession session = sqlSessionFactory.openSession();
        List<Map<String, Object>> list = session.selectList(
                "goodsMapper.getAllGoods", dbParam);
        session.close();
        return list;
    }

    /**
     * 搜索包含指定若干个goods_id的记录
     * 
     * @param dbParam
     * @return
     */
    public List<Map<String, Object>> getGoodsByGoodsIds(
            Map<String, Object> dbParam)
    {
        SqlSession session = sqlSessionFactory.openSession();
        List<Map<String, Object>> list = session.selectList(
                "goodsMapper.getGoodsByGoodsIds", dbParam);
        session.close();
        return list;
    }

    /**
     * 搜索包含指定若干个parent_id的记录
     * 
     * @param dbParam
     * @return
     */
    public List<Map<String, Object>> getGoodsByTypeIds(
            Map<String, Object> dbParam)
    {
        SqlSession session = sqlSessionFactory.openSession();
        List<Map<String, Object>> list = session.selectList(
                "goodsMapper.getGoodsByTypeIds", dbParam);
        session.close();
        return list;
    }
}
