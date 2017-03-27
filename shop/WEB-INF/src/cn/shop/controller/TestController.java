package cn.shop.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/test/")
public class TestController
{
    @RequestMapping(value = "test")
    @ResponseBody
    public Map<String, String> test()
    {
        Map<String, String> map = new HashMap<String, String>();
        map.put("we", "sds");
        System.out.println(map.toString());
        return map;
    }
}
