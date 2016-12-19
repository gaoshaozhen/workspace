package cn.plate.service.model;

import java.util.Date;

public class GoodsModel
{
    String goodsId = null; // 商品编码
    String category = null; // 商品类别
    String goodsName = null; // 商品名称
    int proce = -1; // 商品价格
    Date createTime = null; // 创建时间
    Date loadTime = null; // 上架时间
    Date offLoad = null; // 商品下架时间
    String describe = null; // 商品描述
    String remarks = null; // 商品备注

    public String getGoodsId()
    {
        return goodsId;
    }

    public void setGoodsId(String goodsId)
    {
        this.goodsId = goodsId;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getGoodsName()
    {
        return goodsName;
    }

    public void setGoodsName(String goodsName)
    {
        this.goodsName = goodsName;
    }

    public int getProce()
    {
        return proce;
    }

    public void setProce(int proce)
    {
        this.proce = proce;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public Date getLoadTime()
    {
        return loadTime;
    }

    public void setLoadTime(Date loadTime)
    {
        this.loadTime = loadTime;
    }

    public Date getOffLoad()
    {
        return offLoad;
    }

    public void setOffLoad(Date offLoad)
    {
        this.offLoad = offLoad;
    }

    public String getDescribe()
    {
        return describe;
    }

    public void setDescribe(String describe)
    {
        this.describe = describe;
    }

    public String getRemarks()
    {
        return remarks;
    }

    public void setRemarks(String remarks)
    {
        this.remarks = remarks;
    }
}
