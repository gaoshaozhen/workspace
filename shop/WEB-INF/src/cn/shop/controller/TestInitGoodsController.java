package cn.shop.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ContextLoader;

import cn.shop.model.Goods;
import cn.shop.model.GoodsList;

@Controller
@RequestMapping(value = "/test/")
public class TestInitGoodsController
{
    static ApplicationContext context = ContextLoader
            .getCurrentWebApplicationContext();

    @RequestMapping(value = "init")
    public String init()
    {
        GoodsList goodsList = (GoodsList) context.getBean("goodsList");
        for (int i = 0; i < 10; i++)
        {
            Goods goods = new Goods();
            goods.setGoodsName("商品" + i);
            goods.setPrice((double) i * 100);
            goods.setInfo("说明：" + Math.random() * 1000);
            goodsList.addGoods(goods);
        }
        // modelMap.addAttribute("goodsMap", goodsList.getAllGoods());
        return "init";
    }
}
