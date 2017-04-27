package cn.shop.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * 邮件服务
 * 
 * @author shaozhen
 * @param <K>
 * @param <V>
 */
public class SmtpDao
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
    public Object getSmtp(Map<String, Object> param)
    {
        // SqlSession session = sqlSessionFactory.openSession();
        // List<Object> list;
        // list = session.selectList("smtpMapper.getSmtp", param);
        // session.close();
        return null;
    }

    /**
     * 获得邮件列表
     * 
     * @param <K>
     * @param <V>
     * @return
     */
    public Map<String, Object> getDefaultSmtp()
    {
        SqlSession session;
        Map<String, Object> map;
        Map<String, Integer> param = new HashMap<String, Integer>();

        param.put("id", 1);
        session = sqlSessionFactory.openSession();
        map = session.selectOne("smtpMapper.getSmtpById", param);
        session.close();
        return map;
    }

    /**
     * 新增邮件
     */
    public void addSmtp(Map<String, Object> param)
    {
        // SqlSession session = sqlSessionFactory.openSession();
        // session.insert("smtpMapper.insertSmtp", param);
    }

    /**
     * 删除邮件
     */
    public Object deleteSmtp(Map<String, Object> param)
    {
        // SqlSession session = sqlSessionFactory.openSession();
        // session.insert("smtpMapper.insertSmtp", param);
        return null;
    }

    /**
     * 获取邮件服务信息。
     * 
     * @param param
     * @return
     */
    public Object getSmtpInfo(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        Object object = session.selectOne("smtpMapper.getSmtpById", param);
        session.close();
        return object;
    }

    /**
     * 更新邮件服务信息。
     * 
     * @param param
     */
    public void updateSmtp(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        session.update("smtpMapper.updateSmtp", param);
        session.close();
    }
}
