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
import cn.shop.dao.StoreDao;

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
        StoreDao storeDao;
        Map<String, Object> result = new HashMap<String, Object>();
        List<Map<String, Object>> list;
        List<Map<String, Object>> warnList;
        storeDao = (StoreDao)SpringContextUtil.getBean("storeDao");
        goodsCatDao = (GoodsCatDao) SpringContextUtil.getBean("goodsCatDao");
        list = goodsCatDao.geAlltGoodsCat();
        warnList = storeDao.getAllWarn();
        for(Map<String, Object> map : warnList)
        {
            if(map.get("cat_id") != null)
            {
                Integer warnCatId = (Integer)map.get("cat_id");
                for(Map<String, Object> temp : list)
                {
                    Integer catId = (Integer)temp.get("cat_id");
                    if(catId.compareTo(warnCatId) == 0)
                    {
                        temp.put("warn_num", map.get("warn_num"));
                    }
                }                                
            }
        }
        result.put("catList", list);
        return result;
    }
}
