package cn.shop.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ContextLoader;

import cn.shop.dao.ProductDao;

/**
 * 产品处理类
 * 
 * @author shaozhen
 */
@Controller
@RequestMapping(value = "/product/")
public class ProductController
{
    private static Logger logger = Logger.getLogger(OrderController.class);

    /**
     * 获得产品列表
     * 
     * @return
     */
    @RequestMapping(value = "getProduct.action")
    public Object getProduct(@RequestParam Map<String, String> param)
    {
        ApplicationContext context;
        ProductDao dao;
        context = ContextLoader.getCurrentWebApplicationContext();

        dao = (ProductDao) context.getBean("productDao");

        return dao.getProduct(param);
    }

    /**
     * 新增产品
     */
    @RequestMapping(value = "getProduct.action")
    public Object addProduct(@RequestParam Map<String, String> param)
    {
        Map<String, Object> result = new HashMap<String, Object>();
        ApplicationContext context;
        ProductDao dao;
        context = ContextLoader.getCurrentWebApplicationContext();

        dao = (ProductDao) context.getBean("productDao");
        if (dao.addProduct(param))
        {
            result.put("operator", true);
        }
        else
        {
            result.put("operator", false);
        }
        return null;
    }

    /**
     * 删除产品
     */
    @RequestMapping(value = "getProduct.action")
    public Object deleteProduct(@RequestParam Map<String, String> param)
    {
        Map<String, Object> result = new HashMap<String, Object>();
        ApplicationContext context;
        ProductDao dao;
        context = ContextLoader.getCurrentWebApplicationContext();

        dao = (ProductDao) context.getBean("productDao");
        if (dao.deleteProduct(param))
        {
            result.put("operator", true);
        }
        else
        {
            result.put("operator", false);
        }
        return result;
    }
}
