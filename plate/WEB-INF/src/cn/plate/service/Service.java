package cn.plate.service;

import cn.gsz.tools.ContextTool;
import cn.plate.javaBean.UserBean;
import cn.plate.service.dao.Dao;

public class Service implements UserControl
{
    Dao dao = (Dao) ContextTool.getBean("dao");

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
