package cn.shop.model;


public class Order
{
    String orderId;
    Goods goods;
    int number;
    Long createTime;

    public String getOrderId()
    {
        return orderId;
    }

    public Goods getGoods()
    {
        return goods;
    }

    public int getNumber()
    {
        return number;
    }

    public Long getCreateTime()
    {
        return createTime;
    }

    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
    }

    public void setGoods(Goods goods)
    {
        this.goods = goods;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }

    public void setCreateTime(Long createTime)
    {
        this.createTime = createTime;
    }
}
