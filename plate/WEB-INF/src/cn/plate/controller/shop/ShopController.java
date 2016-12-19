package cn.plate.controller.shop;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/shop/")
public class ShopController
{
    Logger log = Logger.getLogger(ShopController.class);

    @RequestMapping(value = "/index")
    public String shop()
    {
        return "shop/index.jsp";
    }
}
