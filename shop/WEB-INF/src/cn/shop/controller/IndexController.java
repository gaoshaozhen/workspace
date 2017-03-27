package cn.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页访问
 * 
 * @author shaozhen
 */
@Controller
@RequestMapping(value = "/index/")
public class IndexController
{
    @RequestMapping(value = "**")
    public String index()
    {
        return "index";
    }
}
