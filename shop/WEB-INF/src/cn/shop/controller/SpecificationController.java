package cn.shop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;

import cn.shop.base.util.SpringContextUtil;
import cn.shop.dao.SpecificationDao;

/**
 * 规格处理类。
 * 
 * @author shaozhen
 */
@Controller
@RequestMapping(value = "/spec/")
public class SpecificationController
{
    private static Logger logger = Logger
            .getLogger(SpecificationController.class);

    /**
     * 获得规格列表
     * 
     * @return
     */
    @RequestMapping(value = "getSpec.shtm", method = RequestMethod.POST)
    @ResponseBody
    public Object getSpecification(@RequestParam Map<String, Object> param)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        List<?> list;
        SpecificationDao dao = (SpecificationDao) SpringContextUtil
                .getBean("specificationDao");
        list = (List<?>) dao.getSpecification(param);
        map.put("tatal", list.size());
        map.put("data", list);
        return map;
    }

    /**
     * 新增规格
     */
    @RequestMapping(value = "addSpec.action")
    @ResponseBody
    public Object addSpecification(@RequestParam Map<String, Object> param)
    {
        List<?> list;
        Map<String, Object> map = new HashMap<String, Object>();
        ApplicationContext context = ContextLoader
                .getCurrentWebApplicationContext();
        SpecificationDao dao = (SpecificationDao) context
                .getBean("specificationDao");

        if (param.get("spec_name") != null)
        {
            list = (List<?>) dao.getSpecification(param);
            if (list != null && list.size() == 0)
            {
                dao.addSpecification(param);
                map.put("operator", true);
            }
            else
            {
                map.put("operator", false);
            }
        }
        else
        {
            map.put("operator", false);
        }
        return map;
    }

    /**
     * 删除规格
     */
    @RequestMapping(value = "deleteSpec.action")
    @ResponseBody
    public Object deleteSpecification(@RequestParam Map<String, Object> param)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        ApplicationContext context = ContextLoader
                .getCurrentWebApplicationContext();
        SpecificationDao dao = (SpecificationDao) context
                .getBean("specificationDao");
        if (param.get("spec_ids") != null)
        {
            dao.deleteSpecification(param);
            map.put("operator", true);
        }
        else
        {
            map.put("operator", false);
        }
        return map;
    }

    /**
     * 删除规格
     */
    @RequestMapping(value = "updateSpec.action")
    @ResponseBody
    public Object updateSpecification(@RequestParam Map<String, Object> param)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        ApplicationContext context = ContextLoader
                .getCurrentWebApplicationContext();
        SpecificationDao dao = (SpecificationDao) context
                .getBean("specificationDao");
        if (param.get("spec_id") != null)
        {
            dao.updateSpecification(param);
            map.put("operator", true);
        }
        else
        {
            map.put("operator", false);
        }
        return null;
    }
}
