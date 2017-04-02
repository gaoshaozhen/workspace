package cn.shop.base;

import java.util.regex.Pattern;

/**
 * 交易阶段
 * 
 * @version 1.0 2017.4.2
 * @author shaozhen
 */
public enum TradePeriod
{
    NOT_PAY(1, "尚未付款"), PAID(2, "已经付款"), UNKNOWN(-1, "未知类型");

    private int code;
    private String name;

    TradePeriod(int code, String name)
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
    public static TradePeriod get(int code)
    {
        for (TradePeriod t : TradePeriod.values())
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
    public static TradePeriod get(String code)
    {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]+$");
        if (pattern.matcher(code).matches())
        {
            return get(Integer.parseInt(code));
        }
        else
        {
            return UNKNOWN;
        }
    }
}
