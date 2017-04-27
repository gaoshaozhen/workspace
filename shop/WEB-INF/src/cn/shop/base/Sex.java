package cn.shop.base;

import java.util.regex.Pattern;

public enum Sex
{
    MAN(0, "男"),

    WOMEN(1, "女"),

    UNKNOWN(-1, "未知");

    private int code;
    private String desc;

    Sex(int code, String desc)
    {
        this.code = code;
        this.desc = desc;
    }

    public int getCode()
    {
        return this.code;
    }

    public String getDesc()
    {
        return this.desc;
    }

    /**
     * 循环获得类型。
     * 
     * @param code
     * @return TradePeriod
     */
    public static Sex get(int code)
    {
        for (Sex t : Sex.values())
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
    public static Sex get(String code)
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
