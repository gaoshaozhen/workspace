package cn.plate.service;

import java.util.List;

import cn.plate.javaBean.UserBean;
import cn.plate.service.dao.Dao;

// 用户管理
public class UserService implements UserControl
{
    Dao dao;

    public void setDao(Dao dao)
    {
        this.dao = dao;
    }

    public boolean isUser(UserBean userBean)
    {
        if (dao.isUser(userBean))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    // 获得所有用户
    @SuppressWarnings("unchecked")
    public List<Object[]> getAllUser()
    {
        List<Object[]> list = null;

        list = (List<Object[]>) dao.getAllUser();
        return list;
    }

    public boolean addUser(UserBean userBean)
    {
        return dao.addUser(userBean);
    }
}
