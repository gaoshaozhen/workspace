package cn.shop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;


public class AddressDao
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
     * 获得收货地址列表
     * 
     * @return
     */
    public List<Map<String, Object>> getAddressBymemberId(
            Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();
        List<Map<String, Object>> list;

        list = session.selectList("addressMapper.getAddressByMemberId", param);
        session.close();
        return list;
    }

    /**
     * 新增收货地址
     */
    public void addAddress(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();

        session.insert("addressMapper.addAddress", param);
        session.close();
    }

    /**
     * 更新收货地址
     */
    public void updateAddressById(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();

        session.insert("addressMapper.updateAddressById", param);
        session.close();
    }

    /**
     * 删除收货地址
     */
    public void deleteAddress(Map<String, Object> param)
    {
        SqlSession session = sqlSessionFactory.openSession();

        session.insert("addressMapper.deleteAddressById", param);
        session.close();
    }
}
