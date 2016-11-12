package cn.plate.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.plate.modelAndView.ServiceModelAndView;

@Controller
@RequestMapping(value = "/service")
public class ServiceController
{
    @RequestMapping(value = "init")
    public ModelAndView display(HttpServletRequest request)
    {
        ServiceModelAndView s = new ServiceModelAndView();
        s.setRequest(request);
        return s.getMv();
    }
}
