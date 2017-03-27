package cn.shop.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class OrderList
{
    List<Order> list = new ArrayList<Order>();
    Map<String, Order> orderMap = new HashMap<String, Order>();

    public String addOrder(Order order)
    {
        synchronized (OrderList.class)
        {
            UUID uuid = UUID.randomUUID();
            String orderId = uuid.toString();
            Long createTime = System.currentTimeMillis();
            order.setOrderId(orderId);
            order.setCreateTime(createTime);
            orderMap.put(orderId, order);
            return orderId;
        }
    }

    public Order getOrder(String orderId)
    {
        synchronized (OrderList.class)
        {
            return orderMap.get(orderId);
        }
    }
}
