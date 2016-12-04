package cn.plate.service;

import java.util.List;
import java.util.Map;

import cn.plate.service.dao.UserDaoInterface;
import cn.plate.service.dao.da.UserDao;
import cn.plate.service.model.UserModel;

public class Door implements UserDaoInterface
{
    UserDaoInterface userDao = null;

    public UserDaoInterface getUserDao()
    {
        return userDao;
    }

    public void setUserDao(UserDao userDao)
    {
        this.userDao = userDao;
    }

    public int addUser(UserModel userModel)
    {
        // TODO Auto-generated method stub
        return userDao.addUser(userModel);
    }

    public int update(UserModel userModel)
    {
        // TODO Auto-generated method stub
        return userDao.update(userModel);
    }

    public int delete(UserModel userModel)
    {
        // TODO Auto-generated method stub
        return userDao.delete(userModel);
    }

    public UserModel getUser(UserModel userModel)
    {
        // TODO Auto-generated method stub
        return userDao.getUser(userModel);
    }

    public List<Map> getAllUser()
    {
        // TODO Auto-generated method stub
        return userDao.getAllUser();
    }

}
