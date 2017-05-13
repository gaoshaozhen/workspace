package cn.shop.base;

import java.util.regex.Pattern;

public enum PayStatus
{
    WAIT(0, "等待确认"),

    CONFIRM(1, "已确认"),
    
    UNKNOWN(-1, "未知");

    int code;
    String desc;

    PayStatus(int code, String desc)
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
    public static PayStatus get(int code)
    {        
        for (PayStatus t : PayStatus.values())
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
    public static PayStatus get(String code)
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
