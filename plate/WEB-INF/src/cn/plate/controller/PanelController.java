package cn.plate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/panel")
public class PanelController
{
    @RequestMapping(value = "/**")
    public String panel()
    {
        return "mainPanel/panel";
    }

    @RequestMapping(value = "/test")
    public String test()
    {
        return "include/test";
    }
}
