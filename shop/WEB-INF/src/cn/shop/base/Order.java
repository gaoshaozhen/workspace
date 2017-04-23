package cn.shop.base;

import java.util.UUID;

/**
 * 订单工具类
 * 
 * @author shaozhen
 */
public class Order
{
    TradePeriod tradePuriod;

    /**
     * 生成订单id
     * 
     * @return
     */
    public static String createProductId()
    {
        UUID uuid = UUID.randomUUID();

        return uuid.toString().replaceAll("-", "");
    }
}
