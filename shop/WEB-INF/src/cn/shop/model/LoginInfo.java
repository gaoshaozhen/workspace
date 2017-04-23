package cn.shop.model;

import cn.shop.base.UserGrade;

/**
 * 登录用户信息类。
 * 
 * @author shaozhen
 */
public class LoginInfo
{
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
}
