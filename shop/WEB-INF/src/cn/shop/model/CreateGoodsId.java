package cn.shop.model;

import java.util.UUID;

public class CreateGoodsId
{
    public static String createId()
    {
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();

        return id;
    }
}
