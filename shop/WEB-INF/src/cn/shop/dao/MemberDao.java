package cn.shop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;


public class MemberDao
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
     * 获得会员列表
     * 
     * @return
     */
    public Object getAllMember(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        List<Object> list;
        list = session.selectList("memberMapper.getAllMember", param);
        session.close();
        return list;
    }

    /**
     * 获得会员
     * 
     * @return
     */
    public Object getMember(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        List<Object> list;
        list = session.selectList("memberMapper.getMember", param);
        session.close();
        return list;
    }

    /**
     * 新增会员
     */
    public void addMember(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        session.insert("memberMapper.insertMember", param);
        session.close();
    }

    /**
     * 删除会员
     */
    public Object deleteMember()
    {
        return null;
    }
}