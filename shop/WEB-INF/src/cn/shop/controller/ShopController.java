package cn.shop.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ContextLoader;

import cn.shop.dao.DaoShop;

/**
 * 处理商品购买流程
 * 
 * @author shaozhen
 */
@Controller
@RequestMapping(value = "/shop/")
public class ShopController
{
    static Logger log = Logger.getLogger(ShopController.class);
    static ApplicationContext context;
    static
    {
        context = ContextLoader.getCurrentWebApplicationContext();
    }
    /**
     * 进入购买页面
     * <P>
     * 传入参数：商品id，商品名称
     * </P>
     * <p>
     * 传出参数：商品名称，商品id，商品价格
     * </p>
     * 
     * @return String
     */
    @RequestMapping(value = "buy")
    public String shop(ModelMap model,
            @RequestParam Map<String, String> param)
    {
        String goodsName;
        String goodsId;
        DaoShop daoShop;
        Integer goodsPrice;
        Map<String, Object> map = new HashMap<String, Object>();

        goodsName = param.get("goodsName");
        goodsId = param.get("goodsId");
        map.put("goodsId", goodsId);
        daoShop = context.getBean("daoShop", DaoShop.class);
        daoShop.getGood(map);
        goodsPrice = (Integer) daoShop.getGood(map).get(0).get("goodsPrice");
        model.addAttribute("goodsName", goodsName);
        model.addAttribute("goodsId", goodsId);
        model.addAttribute("goodsPrice", goodsPrice);
        log.debug(param.toString());
        return "buy";
    }

    /**
     * 将商品加入购物车
     * <p>
     * 传入参数:{"goodsId" : "商品id","goodsNumber" : 商品数量}
     * </p>
     * 
     * @return
     */
    public String addToCart()
    {

        return "";
    }
}
