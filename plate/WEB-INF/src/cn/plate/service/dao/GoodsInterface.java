package cn.plate.service.dao;

import java.util.List;

import cn.plate.service.model.GoodsModel;

public interface GoodsInterface
{
    // 新建产品
    public int createGoods(GoodsModel goodsModel);

    // 删除产品
    public int deletegoods(GoodsModel goodsModel);

    // 更新产品状态
    public int updateGoods(GoodsModel goodsModel);

    // 获得单个产品
    public GoodsModel getGoodsOne(GoodsModel goodsModel);

    // 获得所有产品
    public List<GoodsModel> getAllGoods();
}
