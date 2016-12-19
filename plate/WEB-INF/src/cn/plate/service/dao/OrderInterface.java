package cn.plate.service.dao;

import java.util.List;

import cn.plate.service.model.OrderModel;

public interface OrderInterface
{
    // 新建订单
    public int createOrder(OrderModel orderModel);

    // 删除订单
    public int deleteOrder(OrderModel orderModel);

    // 更新订单状态
    public int updateOrder(OrderModel orderModel);

    // 获得单个订单
    public OrderModel getOrderOne(OrderModel orderModel);

    // 获得所有订单
    public List<OrderModel> getAllOrder();

}
