package cn.shop.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;


public class MemberLvDao
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
     * 获得会员等级列表
     * 
     * @return
     */
    public Map<String, Object> getMemberLvById(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        Map<String, Object> result = session.selectOne(
                "memberLvMapper.getMemberLvById", param);
        session.close();
        return result;
    }

    /**
     * 新增会员等级
     */
    public void addMemberLv(Map<String, Object> param)
    {
        // SqlSession session = sqlSessionFactory.openSession();
        // session.insert("memberLvMapper.insertMemberLv", param);
    }

    /**
     * 删除会员等级
     */
    public Object deleteMemberLv()
    {
        return null;
    }
}
