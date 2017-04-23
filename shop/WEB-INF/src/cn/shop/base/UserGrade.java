package cn.shop.base;

/**
 * 会员等级
 * 
 * @author shaozhen
 */
public enum UserGrade
{
    COMMON(1, "普通会员"),

    SILVER(2, "白银会员"),

    GOLD(3, "黄金会员");
    
    private int code;
    private String desc;
    
    public int getCode()
    {
        return code;
    }

    public String getDesc()
    {
        return desc;
    }

    UserGrade(int code, String desc)
    {
        this.code = code;
        this.desc = desc;
    }
}
