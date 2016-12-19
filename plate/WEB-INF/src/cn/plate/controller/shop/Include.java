package cn.plate.controller.shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/include/")
public class Include
{
    @RequestMapping(value = "mother")
    public String mother()
    {

        return "include/mother.html";
    }
}
