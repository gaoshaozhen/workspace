package cn.shop.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.shop.annotation.AuthCheck;
import cn.shop.base.util.SpringContextUtil;
import cn.shop.dao.SiteDao;

@Controller
@RequestMapping(value = "/site/")
public class SiteController
{
    @AuthCheck(json = true, login = true, loginTarge = "")
    @RequestMapping(value = "getSite.shtm")
    @ResponseBody
    public Object getSite()
    {
        Map<String, Object> result = new HashMap<String, Object>();
        SiteDao siteDao = (SiteDao) SpringContextUtil.getBean("siteDao");
        Map<String, Object> siteInfo = siteDao.getSite();
        result.put("info", siteInfo);
        result.put("code", 1);
        return result;
    }

    @AuthCheck(json = true, login = true, loginTarge = "")
    @RequestMapping(value = "updateSite.shtm")
    @ResponseBody
    public Object updateSite(@RequestParam Map<String, String> param)
    {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> site = new HashMap<String, Object>();
        SiteDao siteDao = (SiteDao) SpringContextUtil.getBean("siteDao");

        siteDao.updateSite(site);
        result.put("code", 1);
        return result;
    }
}
