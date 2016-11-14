package cn.plate.service;

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

    public boolean addUser(UserBean userBean)
    {
        if (dao.addUser(userBean))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
