package cn.shop.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.shop.base.util.SpringContextUtil;
import cn.shop.dao.OrderDao;

@Controller
@RequestMapping(value = "/mamager/")
public class OrderManager
{
    public Object managerOrder(@RequestParam Map<String, String> param)
    {
        int orderId = NumberUtils.toInt(param.get("orderId"), -1);
        Map<String, Object> result = new HashMap<String, Object>();
        OrderDao orderDao = (OrderDao) SpringContextUtil.getBean("orderDao");
        Map<String, Object> dbParam = new HashMap<String, Object>();

        if (orderId > 0)
        {
            dbParam.put("orderId", orderId);

            result.put("code", "1");
            result.put("desc", "sucecess");
        }
        else
        {
            result.put("code", "0");
            result.put("desc", "fail");
        }
        return result;
    }
}
