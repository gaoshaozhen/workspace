package cn.shop.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("siteDao")
public class SiteDao
{
    @Autowired
    SqlSessionFactory sqlSessionFactory;
    
    /**
     * 
     * @return
     */
    public Map<String, Object> getSite()
    {
        SqlSession session = sqlSessionFactory.openSession();
        Map<String, Object> list;
        list = session.selectOne("siteMapper.getSite");
        session.close();
        return list;
    }

    /**
     * 
     * @return
     */
    public void updateSiteName(
            Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();        

        session.update("siteMapper.updateSiteName", param);
        session.close();        
    }
    
    public void updateTitle(
            Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();        

        session.update("siteMapper.updateTitle", param);
        session.close();        
    }
    
    public void updateIconFile(
            Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();        

        session.update("siteMapper.updateIconFile", param);
        session.close();        
    }
    public void updateLogoFile(
            Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();        

        session.update("siteMapper.updateLogoFile", param);
        session.close();        
    }
    
    public void updateKeywords(
            Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();        

        session.update("siteMapper.updateKeywords", param);
        session.close();        
    }
}
