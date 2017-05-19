package cn.shop.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
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
        int total = 0;
        SqlSession session = sqlSessionFactory.openSession();
        Map<String, Object> result = session
                .selectOne("goodsMapper.getAllGoodsTotal");

        if (result != null)
        {
            total = NumberUtils.toInt(result.get("total").toString(), 0);
            session.close();
        }
        return total;
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
     * 搜索包含指定若干个parent_id的记录。
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

    /**
     * 搜索包含指定若干个parent_id的记录。
     * 
     * @param dbParam
     * @return
     */
    public int getTotalGoodsByTypeIds(Map<String, Object> dbParam)
    {
        int total = 0;
        SqlSession session = sqlSessionFactory.openSession();
        Map<String, Object> map = session.selectOne(
                "goodsMapper.getTotalGoodsByTypeIds", dbParam);
        session.close();
        total = NumberUtils.toInt(map.get("total").toString(), 0);
        return total;
    }

    /**
     * 按照goodsId查找。
     * 
     * @param dbParam
     * @return
     */
    public List<Map<String, Object>> getGoodsByGoodsId(
            Map<String, Object> dbParam)
    {
        SqlSession session = sqlSessionFactory.openSession();
        List<Map<String, Object>> list = session.selectList(
                "goodsMapper.getGoodsByGoodsId", dbParam);

        session.close();
        return list;
    }

    /**
     * 查找goods详细信息。
     */
    public Map<String, Object> getOneGoodsByGoodsId(Map<String, Object> param)
    {
        Map<String, Object> result;
        SqlSession session = sqlSessionFactory.openSession();
        result = session.selectOne("goodsMapper.getOneGoodsByGoodsId", param);

        session.close();
        return result;
    }

    public void addGoods(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();

        session.selectOne("goodsMapper.addGoods", param);
        session.close();
    }
    
    public Map<String, Object> getOneGoodsBySn(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        Map<String, Object> map = session.selectOne("goodsMapper.getOneGoodsBySn", param);
        session.close();
        return map;
    }

    public List<Map<String, Object>> getGoodsList(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        List<Map<String, Object>> list = session.selectList(
                "goodsMapper.getGoodsList", param);
        session.close();
        return list;
    }
    
    public void update(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        session.update("goodsMapper.updateGoods", param);
        session.close();
    }
    
    public void updateBase(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        session.update("goodsMapper.updateGoodsBase", param);
        session.close();
    }
    
    public void updateIntro(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        session.update("goodsMapper.updateGoodsIntro", param);
        session.close();
    }
}
