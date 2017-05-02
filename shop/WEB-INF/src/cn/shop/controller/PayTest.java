package cn.shop.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.shop.base.util.HttpClientUtil;

/**
 * 模拟第三方收款
 * @author shaozhen
 *
 */
@Controller
@RequestMapping(value="/paytest/")
public class PayTest
{
    Logger logger = Logger.getLogger(this.getClass());
    
    @RequestMapping(value="pay.shtm", method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Object payTest()
    {
        Map<String, Object> result = new HashMap<String, Object>();
        
        result.put("code", 1);
        result.put("desc", "success");
        HttpClientUtil.sendHttpPostJson("http://localhost:8080/shop/paytest/pay.shtm", "{\"we\":\"test\"}");
        return result;
    }
}
