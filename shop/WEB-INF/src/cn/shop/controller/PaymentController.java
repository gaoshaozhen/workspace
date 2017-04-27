package cn.shop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.shop.base.util.JsonUtil;
import cn.shop.base.util.SpringContextUtil;
import cn.shop.dao.PaymentDao;

/**
 * 支付方式处理类。
 * 
 * @author shaozhen
 */
@Controller
@RequestMapping(value = "/payment/")
public class PaymentController
{
    /**
     * 获得支付方式列表
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping(value = "getPaymentList.shtm", method = RequestMethod.POST)
    public Object getPayment(@RequestParam Map<String, String> param,
            HttpSession session)
    {
        PaymentDao paymentDao;
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> dbParam = new HashMap<String, Object>();
        List<Map<String, Object>> list;

        paymentDao = (PaymentDao) SpringContextUtil.getBean("paymentDao");
        list = (List<Map<String, Object>>) paymentDao.getPayment(dbParam);
        for (Map<String, Object> temp : list) // 将字符串转化为json对象，以直接让浏览器解析为json对象
        {
            String params = (String) temp.get("config");

            if (params != null)
            {
                temp.put("config", JsonUtil.getJSONObject(params));
            }
        }
        map.put("data", list);
        map.put("total", list.size());
        return map;
    }

    /**
     * 新增支付方式
     */
    public Object addPayment()
    {
        return null;
    }

    /**
     * 删除支付方式
     */
    public Object deletePayment()
    {
        return null;
    }
}
