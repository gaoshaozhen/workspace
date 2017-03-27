package cn.shop.model;

public class Goods
{
    String goodsId;
    String goodsName;
    Double price;
    String info;
    Long createTime;

    public String getGoodsId()
    {
        return goodsId;
    }

    public String getGoodsName()
    {
        return goodsName;
    }

    public Double getPrice()
    {
        return price;
    }

    public String getInfo()
    {
        return info;
    }

    public void setGoodsId(String goodsId)
    {
        this.goodsId = goodsId;
    }

    public void setGoodsName(String goodsName)
    {
        this.goodsName = goodsName;
    }

    public void setPrice(Double price)
    {
        this.price = price;
    }

    public void setInfo(String info)
    {
        this.info = info;
    }

    public Long getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Long createTime)
    {
        this.createTime = createTime;
    }
}
