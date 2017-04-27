package cn.shop.base;

import java.util.regex.Pattern;

public enum OrderStatus
{
    WAIT(0, "等待付款"),

    WAITINGCONFIRM(1, "已付款等待确认"),

    PAYED(2, "已付款待确认"),

    DISTRIBUTING(3, "配货中"),

    DELIVERED(4, "已发货"),

    CANCEL(5, "已取消"),

    SUCCESS(6, "成功"),

    UNKNOWN(-1, "未知");

    int code;
    String desc;

    OrderStatus(int code, String desc)
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
    public static OrderStatus get(int code)
    {
        for (OrderStatus t : OrderStatus.values())
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
    public static OrderStatus get(String code)
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
