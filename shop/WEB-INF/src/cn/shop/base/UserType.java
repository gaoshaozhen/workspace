package cn.shop.base;

import java.util.regex.Pattern;

/**
 * 用户类型
 * 
 * @version 1.0 2017.4.2
 * @author shaozhen
 */
public enum UserType
{
    SYSTEM_MANAGER(1, "系统管理员"),

    MANAGER(2, "普通管理员"),

    SENIOR_MEMBER(3, "黄金会员"),

    INTERMEDIATE_MEMBER(4, "白银会员"),

    REGULAR_MEMBER(5, "普通会员"),

    VISITOR(6, "普通游客"),

    UNKNOWN(-1, "未知类型");

    int code;
    String name;

    UserType(int code, String name)
    {
        this.code = code;
        this.name = name;
    }

    public int getCode()
    {
        return this.code;
    }

    public String getName()
    {
        return this.name;
    }

    /**
     * 循环获得类型。
     * 
     * @param code
     * @return TradePeriod
     */
    public static UserType get(int code)
    {
        for (UserType t : UserType.values())
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
    public static UserType get(String code)
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
