package cn.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/buying/")
public class BuyingController
{
    @RequestMapping(value = "buying")
    public String buy()
    {
        return "buying";
    }
}
