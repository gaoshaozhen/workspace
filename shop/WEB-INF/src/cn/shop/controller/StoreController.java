package cn.shop.controller;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.shop.annotation.AuthCheck;
import cn.shop.base.util.SpringContextUtil;
import cn.shop.dao.StoreDao;

@Controller
@RequestMapping(value = "/store/")
public class StoreController
{
    @AuthCheck(login = true, json = true, loginTarge = "")
    @RequestMapping(value = "getStore.shtm")
    @ResponseBody
    public Object getStore()
    {
        Map<String, Object> result = new HashMap<String, Object>();
        StoreDao storeDao = (StoreDao) SpringContextUtil.getBean("storeDao");
        List<Map<String, Object>> list = storeDao.getAllWarn();
        result.put("data", list);
        return result;
    }

    @AuthCheck(login = true, json = true, loginTarge = "")
    @RequestMapping(value = "updateStore.shtm")
    @ResponseBody
    public Object updateStore(
            @RequestParam(value = "store", defaultValue = "0") Integer warnNum,
            @RequestParam(value = "catId", required=false) BigInteger catId,
            @RequestParam(value = "disabled", required=false) Integer disabled,
            @RequestParam(value = "productId", required=false) BigInteger productId)
    {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> dbParam = new HashMap<String, Object>();
        StoreDao storeDao = (StoreDao) SpringContextUtil.getBean("storeDao");
        List<Map<String, Object>> list;
        dbParam.put("warnNum", warnNum);
        
        if(catId == null && productId == null)
        {
            return result;
        }
        if (productId != null)
        {
            dbParam.put("productId", productId);
        }
        if (catId != null)
        {
            dbParam.put("catId", catId);
        }
        if(disabled != null)
        {
            dbParam.put("disabled", disabled);
        }
        list = storeDao.getStore(dbParam);
        if (list.isEmpty())
        {
            dbParam.put("disabled", 1);
            storeDao.addWarn(dbParam);
        }
        else
        {
            storeDao.updateWarn(dbParam);
        }
        return result;
    }
}
