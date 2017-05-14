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
    public List<Map<String, Object>> getAllMember(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        List<Map<String, Object>> list;
        list = session.selectList("memberMapper.getAllMember", param);
        session.close();
        return list;
    }
    /**
     * 获得会员列表
     * 
     * @return
     */
    public long getAllMemberTotal(Map<String, Object> param)
    {
        long total = 0;
        SqlSession session = sqlSessionFactory.openSession();
        Map<String, Object> list;
        list = session.selectOne("memberMapper.getAllMemberTotal", param);
        session.close();
        total = (Long)list.get("total");
        return total;
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
     * 获得会员
     * 
     * @return
     */
    public Map<String, Object> getOneMemberById(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        Map<String, Object> map= session.selectOne("memberMapper.getOneMemberById", param);
        session.close();
        return map;
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
