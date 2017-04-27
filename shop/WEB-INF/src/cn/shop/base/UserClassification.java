package cn.shop.base;

import java.util.regex.Pattern;

public enum UserClassification
{
    ADMIN(1, "系统用户"),

    MEMBER(2, "会员用户"),

    UNKNOWN(-1, "未知类型");

    int code;
    String desc;

    public int getCode()
    {
        return this.code;
    }

    public String getDesc()
    {
        return this.desc;
    }

    UserClassification(int code, String desc)
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
    public static UserClassification get(int code)
    {
        for (UserClassification t : UserClassification.values())
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
    public static UserClassification get(String code)
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
