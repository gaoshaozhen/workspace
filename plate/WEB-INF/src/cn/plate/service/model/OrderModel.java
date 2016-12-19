package cn.plate.service.model;

import java.util.Date;

public class OrderModel
{
    String seller = null; // 商家
    String orderId = null;// 订单编码
    String goodsId = null;// 商品编码
    String goodsName = null;// 商品名称
    int goodsNumber = -1;// 商品数量
    int moneyNumber = -1;// 订单价格
    String sendStatus = null;// 发货状态
    String customerName = null;// 消费者姓名
    String customerConnection = null;// 消费者联系方式
    String customerAddress = null;// 消费者预留地址
    Date createTime = null;// 订单创建时间
    String OrderStatus = null;// 订单状态

    public String getSeller()
    {
        return seller;
    }

    public void setSeller(String seller)
    {
        this.seller = seller;
    }

    public String getOrderId()
    {
        return orderId;
    }

    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
    }

    public String getGoodsId()
    {
        return goodsId;
    }

    public void setGoodsId(String goodsId)
    {
        this.goodsId = goodsId;
    }

    public String getGoodsName()
    {
        return goodsName;
    }

    public void setGoodsName(String goodsName)
    {
        this.goodsName = goodsName;
    }

    public int getGoodsNumber()
    {
        return goodsNumber;
    }

    public void setGoodsNumber(int goodsNumber)
    {
        this.goodsNumber = goodsNumber;
    }

    public int getMoneyNumber()
    {
        return moneyNumber;
    }

    public void setMoneyNumber(int moneyNumber)
    {
        this.moneyNumber = moneyNumber;
    }

    public String getSendStatus()
    {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus)
    {
        this.sendStatus = sendStatus;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }

    public String getCustomerConnection()
    {
        return customerConnection;
    }

    public void setCustomerConnection(String customerConnection)
    {
        this.customerConnection = customerConnection;
    }

    public String getCustomerAddress()
    {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress)
    {
        this.customerAddress = customerAddress;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public String getOrderStatus()
    {
        return OrderStatus;
    }

    public void setOrderStatus(String orderStatus)
    {
        OrderStatus = orderStatus;
    }
}
