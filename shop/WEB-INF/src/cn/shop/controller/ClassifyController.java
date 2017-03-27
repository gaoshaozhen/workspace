package cn.shop.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 首页访问
 * 
 * @author shaozhen
 */
@Controller
@RequestMapping(value = "/classify/")
public class ClassifyController
{
    static Logger log = Logger.getLogger(ClassifyController.class);
    @RequestMapping(value = "classify")
    public String classify()
    {
        return "classify";
    }

    /**
     * 返回分类数据
     * 
     * @param param
     * @return
     */
    @RequestMapping(value = "fy")
    public String setClassify(ModelMap model,
            @RequestParam Map<String, String> param)
    {
        log.debug(model.toString());
        model.addAttribute("name", param.get("name"));
        return "api/api_classify";
    }
}
