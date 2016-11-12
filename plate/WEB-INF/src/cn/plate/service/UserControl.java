package cn.plate.service;

import cn.plate.javaBean.UserBean;

public interface UserControl
{
    public boolean isUser(UserBean userBean);

    public boolean addUser(UserBean userBean);
    // public boolean addUser(UserBean userBean);
}
