package cn.shop.base;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class IdManager
{
    public static String createSn()
    {
        StringBuilder build = new StringBuilder();
        Date date = new Date();
        Random random = new Random();
        final String pattern = "yyyyMMddHHmmss";
        SimpleDateFormat format = new SimpleDateFormat(pattern);

        build.append(format.format(date));
        for (int i = 0; i < 5; i++)
        {
            build.append(random.nextInt(9));
        }
        return build.toString();
    }
    
    public static int createGoodsId()
    {
        int id = 0;
        Date date = new Date();
        Calendar cal = Calendar.getInstance();

        cal.setTime(date);
        id = cal.get(Calendar.YEAR);
        id *= 10000;
        Random random = new Random();
        id += random.nextInt(9999);

        return id;
    }
    
    public static int createProductId()
    {
        int id = 0;
        Date date = new Date();
        Calendar cal = Calendar.getInstance();

        cal.setTime(date);
        id = cal.get(Calendar.YEAR);
        id *= 10000;
        Random random = new Random();
        id += random.nextInt(9999);

        return id;
    }
}
