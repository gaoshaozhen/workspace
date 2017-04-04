package cn.shop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * 邮件服务
 * 
 * @author shaozhen
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
        SqlSession session = sqlSessionFactory.openSession();
        List<Object> list;
        list = session.selectList("smtpMapper.getSmtp", param);
        session.close();
        return list;
    }

    /**
     * 新增邮件
     */
    public void addSmtp(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        session.insert("smtpMapper.insertSmtp", param);
    }

    /**
     * 删除邮件
     */
    public Object deleteSmtp(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        session.insert("smtpMapper.insertSmtp", param);
        return null;
    }
}
