package cn.plate.service.model;

public class UserModel
{
    int userId;
    String username;
    String password;
    int powerId;

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

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public int getPowerId()
    {
        return powerId;
    }

    public void setPowerId(int powerId)
    {
        this.powerId = powerId;
    }

}
