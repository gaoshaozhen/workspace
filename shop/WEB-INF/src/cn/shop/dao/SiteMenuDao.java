package cn.shop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * 加载菜单栏。
 * 
 * @author shaozhen
 */
public class SiteMenuDao
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
     * 获得菜单栏列表
     * 
     * @return
     */
    public List<Map<String, Object>> getSiteMenuBymemberId(
            Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        List<Map<String, Object>> list;

        list = session
                .selectList("siteMenuMapper.getSiteMenuByMemberId", param);
        session.close();
        return list;
    }

    /**
     * 获得菜单栏列表
     * 
     * @return
     */
    public List<Map<String, Object>> getAllSiteMenuList()
    {
        SqlSession session = sqlSessionFactory.openSession();
        List<Map<String, Object>> list;

        list = session.selectList("siteMenuMapper.getAllSiteMenuList");
        session.close();
        return list;
    }

    /**
     * 新增菜单栏
     */
    public void addSiteMenu(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();

        session.insert("siteMenuMapper.addSiteMenu", param);
        session.close();
    }

    /**
     * 更新菜单栏
     */
    public void updateSiteMenuById(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();

        session.insert("siteMenuMapper.updateSiteMenuById", param);
        session.close();
    }

    /**
     * 删除菜单栏
     */
    public void deleteSiteMenu(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();

        session.insert("siteMenuMapper.deleteSiteMenuById", param);
        session.close();
    }
}
