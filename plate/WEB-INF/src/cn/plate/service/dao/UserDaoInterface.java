package cn.plate.service.dao;

import java.util.List;
import java.util.Map;

import cn.plate.service.model.UserModel;

public interface UserDaoInterface
{
    public int addUser(UserModel userModel);

    public int update(UserModel userModel);

    public int delete(UserModel userModel);

    public UserModel getUser(UserModel userModel);

    public List<Map> getAllUser();
}
