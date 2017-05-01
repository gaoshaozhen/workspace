package cn.shop.base.util;

public class Default
{
    public static <T> T get(T value, T defaultValue)
    {        
        if (value == null)
        {
            return defaultValue;
        }
        else
        {
            return value;
        }
    }
    
    public static String  toString(Object value, String defaultValue)
    {        
        if (value == null)
        {
            return defaultValue;
        }
        else
        {
            return value.toString();
        }
    }    
}
