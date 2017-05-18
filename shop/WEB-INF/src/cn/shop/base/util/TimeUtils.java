package cn.shop.base.util;

public class TimeUtils
{
    public static String formatToDefault(Long time, String pattern)
    {
        String str = pattern;
        long h= 0,m=0,s;
        if(pattern.indexOf("HH") >= 0)
        {
            h = time % 3600;
            str.replace("HH", String.format("%02d",String.valueOf(h)));
        }
        if(pattern.indexOf("mm") >=0)
        {
            m = time% 60;
            str.replace("mm", String.format("%02d", String.valueOf(m)));
        }
        if(pattern.indexOf("ss") >= 0)
        {
            s = time - h*60*60 -m*60;
            str.replace("ss", String.valueOf(s) );
        }
        return str;
    }
}
