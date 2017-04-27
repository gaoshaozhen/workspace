package cn.shop.model;

import java.util.Map;

import cn.shop.base.UserGrade;
import cn.shop.base.UserType;
import cn.shop.base.util.Default;

/**
 * 登录用户信息类。
 * 
 * @author shaozhen
 */
public class LoginInfo
{
    int userId;
    String username;
    String password;

    /**
     * 会员等级
     */
    UserGrade UserGrade;
    /*
     * 积分。
     */
    int integral;

    UserType userType;


    public UserType getUserType()
    {
        return userType;
    }

    public void setUserType(UserType userType)
    {
        this.userType = userType;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public UserGrade getUserGrade()
    {
        return UserGrade;
    }

    public int getIntegral()
    {
        return integral;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setUserGrade(UserGrade userGrade)
    {
        UserGrade = userGrade;
    }

    public void setIntegral(int integral)
    {
        this.integral = integral;
    }

    /**
     * 初始化参数。
     * 
     * @param map
     */
    public void setParam(Map<String, Object> map)
    {
        // String gradeStr = map.get("grade")
        // this.setUserGrade(cn.shop.base.UserGrade.get(map.get("grade")));
        Object object = map.get("interval");
        String str = map.get("id").toString();
        this.setUsername(map.get("username").toString());
        this.setPassword(map.get("password").toString());
        this.setUserId((Integer) map.get("id"));
        this.setIntegral((Integer) Default.get(object, 0));
        this.setUserType(UserType.get((Integer) Default.get(
                map.get("usertype"), -1)));

    }
}
