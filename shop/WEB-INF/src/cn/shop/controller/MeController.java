package cn.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/me/")
public class MeController
{
    @RequestMapping(value = "*")
    public String me()
    {
        return "me";
    }
}
