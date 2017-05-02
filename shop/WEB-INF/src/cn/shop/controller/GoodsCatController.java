package cn.shop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.shop.base.util.SpringContextUtil;
import cn.shop.dao.GoodsCatDao;

/**
 * 商品分类处理
 * 
 * @author shaozhen
 */
@Controller
@RequestMapping(value = "/mall/")
public class GoodsCatController
{
    @RequestMapping(value = "getGoodsCatList.shtm")
    @ResponseBody
    public Object getGoodsCatList(@RequestParam Map<String, String> param)
    {
        GoodsCatDao goodsCatDao;
        Map<String, Object> result = new HashMap<String, Object>();
        List<Map<String, Object>> list;

        goodsCatDao = (GoodsCatDao) SpringContextUtil.getBean("goodsCatDao");
        list = goodsCatDao.geAlltGoodsCat();
        result.put("catList", list);
        return result;
    }
}
