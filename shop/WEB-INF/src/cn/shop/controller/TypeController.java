package cn.shop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.shop.base.util.JsonUtil;
import cn.shop.base.util.SpringContextUtil;
import cn.shop.dao.TypeDao;

/**
 * 类型处理类。
 * 
 * @author shaozhen
 */
@Controller
@RequestMapping(value = "/type/")
public class TypeController
{
    /**
     * 获得类型列表
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping(value = "getTypeList.shtm", method = RequestMethod.POST)
    public Object getType(@RequestParam Map<String, String> param,
            HttpSession session)
    {
        int pageSize;
        int pageNumber;
        int start, end;
        TypeDao typeDao;
        Map<String, Object> map;
        Map<String, Object> dbParam = new HashMap<String, Object>();
        List<Map<String, Object>> list;
        
        pageSize = NumberUtils.toInt(param.get("pageSize"), 100);
        pageNumber = NumberUtils.toInt(param.get("pageNumber"), 1);
        start = pageNumber * pageSize - pageSize;
        end = pageSize;
        if (start < 0)
        {
            start = 0;
        }
        if (end < 0)
        {
            end = 0;
        }
        dbParam.put("start", start);
        dbParam.put("end", end);
        typeDao = (TypeDao) SpringContextUtil.getBean("typeDao");
        map = (Map<String, Object>) typeDao.getCount(dbParam);
        list = (List<Map<String, Object>>) typeDao.getType(dbParam);
        for (Map<String, Object> temp : list) // 将字符串转化为json对象，以直接让浏览器解析为json对象
        {
            String paramsStr = (String) temp.get("params");
            String propsStr = (String) temp.get("props");

            if (paramsStr != null)
            {
                temp.put("params", JsonUtil.getJSONArray(paramsStr));
            }
            if (propsStr != null)
            {
                temp.put("props", JsonUtil.getJSONArray(propsStr));
            }
        }
        map.put("data", list);
        map.put("page", pageNumber);
        return map;
    }

    /**
     * 新增类型
     */
    public Object addType()
    {
        return null;
    }

    /**
     * 删除类型
     */
    public Object deleteType()
    {
        return null;
    }
}
