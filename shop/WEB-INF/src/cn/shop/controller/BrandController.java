package cn.shop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;

import cn.shop.dao.BrandDao;

/**
 * 品牌处理类。
 * 
 * @author shaozhen
 */
@Controller
@RequestMapping(value = "/brand/")
public class BrandController
{
    static Logger logger = Logger.getLogger(BrandController.class);

    /**
     * 获得品牌列表
     * 
     * @return
     */
    @RequestMapping(value = "getBrand.action")
    @ResponseBody
    public Object getBrand(@RequestParam Map<String, Object> param)
    {
        logger.debug(param.toString());
        ApplicationContext context = ContextLoader
                .getCurrentWebApplicationContext();
        BrandDao dao = (BrandDao) context.getBean("brandDao");

        return dao.getBrand(param);
    }

    /**
     * 新增品牌
     */
    @RequestMapping(value = "addBrand.action")
    @ResponseBody
    public Object addBrand(@RequestParam Map<String, Object> param)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> result = new HashMap<String, Object>();
        List<?> list;
        logger.debug(param.toString());
        ApplicationContext context = ContextLoader
                .getCurrentWebApplicationContext();
        map.put("name", param.get("name"));
        BrandDao dao = (BrandDao) context.getBean("brandDao");
        list = (List<?>) dao.getBrand(map);
        if (list != null && list.size() == 0)
        {

            dao.addBrand(param);
            result.put("operator", true);
        }
        else
        {
            result.put("operator", false);
        }

        return result;
    }

    /**
     * 删除品牌
     */
    @RequestMapping(value = "deleteBrand.action")
    @ResponseBody
    public Object deleteBrand(@RequestParam Map<String, Object> param)
    {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> dbParam = new HashMap<String, Object>();
        ApplicationContext context = ContextLoader
                .getCurrentWebApplicationContext();
        BrandDao dao = (BrandDao) context.getBean("brandDao");
        dbParam.put("brand_ids", param.get("brand_ids"));
        dao.deleteBrand(dbParam);
        result.put("result", true);
        return result;
    }
}