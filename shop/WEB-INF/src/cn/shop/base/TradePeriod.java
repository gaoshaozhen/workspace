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
    NOT_PAY(1, "尚未付款"),

    PAID(2, "已经付款"),

    CONFIRM(3, "确认收款"),

    FAIL(4, "交易失败"),

    SUCCESS(5, "交易成功"),

    RETURNFEE(6, "退款"),

    UNKNOWN(-1, "未知类型");

    private int code;
    private String name;

    TradePeriod(int code, String name)
    {
        this.code = code;
        this.name = name;
    }

    /**
     * 获得代码值。
     * 
     * @return
     */
    public int getCode()
    {
        return this.code;
    }

    /**
     * 获取描述。
     * 
     * @return
     */
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
