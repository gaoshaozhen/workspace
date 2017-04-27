package cn.shop.base;

import java.util.regex.Pattern;

/**
 * 会员等级
 * 
 * @author shaozhen
 */
public enum UserGrade
{
    COMMON(1, "普通会员"),

    SILVER(2, "白银会员"),

    GOLD(3, "黄金会员"),
    
    UNKNOWN(-1, "未知类型");
    
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
    
    /**
     * 循环获得类型。
     * 
     * @param code
     * @return TradePeriod
     */
    public static UserGrade get(int code)
    {
        for (UserGrade t : UserGrade.values())
        {
            if (t.getCode() == code)
            {
                return t;
            }
        }
        return UNKNOWN;
    }

    /**
     * 重载。
     * 
     * @param code
     * @return TradePeriod
     */
    public static UserGrade get(String code)
    {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]+$");
        if (code != null && pattern.matcher(code).matches())
        {
            return get(Integer.parseInt(code));
        }
        else
        {
            return UNKNOWN;
        }
    }
    
}
