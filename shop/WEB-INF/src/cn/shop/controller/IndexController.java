package cn.shop.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页访问
 * 
 * @author shaozhen
 */
@Controller
@RequestMapping(value = "/view/")
public class IndexController
{
    Logger logger = Logger.getLogger(this.getClass());

    @RequestMapping(value = "index.shtm")
    public String index()
    {
        logger.info("进入首页");
        return "index";
    }
}
