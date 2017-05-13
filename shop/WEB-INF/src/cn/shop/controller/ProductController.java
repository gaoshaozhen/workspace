package cn.shop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.shop.base.util.SpringContextUtil;
import cn.shop.dao.ProductDao;
import cn.shop.model.Page;

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
     * 获得产品库存列表
     * 
     * @return
     */
    @RequestMapping(value = "getProduct.shtm")
    @ResponseBody
    public Object getProduct(@RequestParam Map<String, String> param)
    {
        int start, num, pageNumber;
        String name = StringUtils.trimToNull(param.get("name"));
        ProductDao productDao;
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> productDbParam = new HashMap<String, Object>();
        List<Map<String, Object>> productList;
        pageNumber = NumberUtils.toInt(param.get("pageNumber"), 0);
        num = NumberUtils.toInt(param.get("pageSize"), 20);
        start = Page.getStartNum(num,
                pageNumber);
        productDbParam.put("start", start);
        productDbParam.put("num", num);
        if(name != null)
        {            
            productDbParam.put("name", "%" + name.trim() + "%");
        }
        productDao = (ProductDao) SpringContextUtil.getBean("productDao");
        productList = productDao.getAllProduct(productDbParam);
        result.put("data", productList);
        result.put("total", productDao.getAllProductTotal(productDbParam));
        result.put("pageNumber", pageNumber);
        return result;
    }

    @RequestMapping(value = "updateStore.shtm")
    public Object updateStore(@RequestParam Map<String, Object> param)
    {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> dbParam = new HashMap<String, Object>();
        Integer productId = (Integer) param.get("productId");
        Integer store = (Integer) param.get("store");
        ProductDao productDao;

        if (productId == null || store == null)
        {
            return result;
        }
        productDao = (ProductDao) SpringContextUtil.getBean("productDao");
        dbParam.put("productId", productId);
        dbParam.put("store", store);
        productDao.updateStore(dbParam);
        return result;
    }

    /**
     * 新增产品
     */
    @RequestMapping(value = "addProduct.action")
    public Object addProduct(@RequestParam Map<String, String> param)
    {
        // Map<String, Object> result = new HashMap<String, Object>();
        // ApplicationContext context;
        // ProductDao dao;
        // context = ContextLoader.getCurrentWebApplicationContext();
        //
        // dao = (ProductDao) context.getBean("productDao");
        // if (dao.addProduct(param))
        // {
        // result.put("operator", true);
        // }
        // else
        // {
        // result.put("operator", false);
        // }
        return null;
    }

    /**
     * 删除产品
     */
    @RequestMapping(value = "deleteProduct.action")
    public Object deleteProduct(@RequestParam Map<String, String> param)
    {
        // Map<String, Object> result = new HashMap<String, Object>();
        // ApplicationContext context;
        // ProductDao dao;
        // context = ContextLoader.getCurrentWebApplicationContext();
        //
        // dao = (ProductDao) context.getBean("productDao");
        // if (dao.deleteProduct(param))
        // {
        // result.put("operator", true);
        // }
        // else
        // {
        // result.put("operator", false);
        // }
        return null;
    }
}
