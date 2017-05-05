package cn.shop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.shop.base.util.SpringContextUtil;
import cn.shop.dao.TagDao;

@Controller
@RequestMapping(value = "/tag/")
public class TagController
{
    @RequestMapping(value = "getTag.shtm")
    @ResponseBody
    public Object getTags(@RequestParam Map<String, String> param)
    {
        TagDao tagDao = (TagDao) SpringContextUtil.getBean("tagDao");
        Map<String, Object> result = new HashMap<String, Object>();
        List<Map<String, Object>> list;

        list = tagDao.getAllTag();
        result.put("result", list);
        return result;
    }
}
