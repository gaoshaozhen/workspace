package cn.plate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/system")
public class SystemController
{
    @RequestMapping(value = "/**")
    public String system()
    {
        return "system";
    }
}
