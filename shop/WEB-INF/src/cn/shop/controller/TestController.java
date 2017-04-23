package cn.shop.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.shop.base.Configuration;
import cn.shop.base.util.SpringContextUtil;

@Controller
@RequestMapping(value = "/test/")
public class TestController
{
    @RequestMapping(value = "test")
    @ResponseBody
    public Map<String, String> test(HttpSession session)
    {
        Configuration configuration = (Configuration) SpringContextUtil
                .getBean("configuration");

        return null;
    }
}
