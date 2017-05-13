package cn.shop.base;

import java.util.Random;
import java.util.UUID;

public class IdManager
{
    public static String createSn()
    {
//        StringBuilder build = new StringBuilder();
//        Date date = new Date();
//        Random random = new Random();
//        final String pattern = "yyyyMMddHHmmss";
//        SimpleDateFormat format = new SimpleDateFormat(pattern);
//
//        build.append(format.format(date));
//        for (int i = 0; i < 5; i++)
//        {
//            build.append(random.nextInt(9));
//        }
//        
        UUID uuid = UUID.randomUUID();
        
        return uuid.toString().replaceAll("-", "");
    }
    
    public static int createGoodsId()
    {
        int id = 10000000;
//        Date date = new Date();
//        Calendar cal = Calendar.getInstance();

//        cal.setTime(date);
//        id = cal.get(Calendar.YEAR);
//        id *= 10000;
        Random random = new Random();
        id += random.nextInt(9999999);

        return id;
    }
    
    public static int createProductId()
    {
        int id = 10000000;
//        Date date = new Date();
//        Calendar cal = Calendar.getInstance();
//
//        cal.setTime(date);
//        id = cal.get(Calendar.YEAR);
//        id *= 10000;
        Random random = new Random();
        id += random.nextInt(9999999);

        return id;
    }
}
