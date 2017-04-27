package cn.shop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;

import cn.shop.base.util.SpringContextUtil;
import cn.shop.dao.SmtpDao;

/**
 * 邮件处理
 * 
 * @author shaozhen
 */
@Controller
@RequestMapping(value = "/smtp/")
public class SmtpController
{
    private static Logger logger = Logger.getLogger(SmtpController.class);

    /**
     * 获得邮件列表
     * 
     * @return
     */
    @RequestMapping(value = "getSmtp.action")
    @ResponseBody
    public Object getSmtp(@RequestParam Map<String, Object> param,
            @CookieValue(value = "smtpname", required = false) String smtpname)
    {
        Set<String> set = param.keySet();
        Map<String, Object> map = new HashMap<String, Object>();
        ApplicationContext context = ContextLoader
                .getCurrentWebApplicationContext();

        logger.debug("查询邮件信息");
        for (String key : set)
        {
            logger.debug(key + ":" + param.get(key));
        }        
        SmtpDao smtpDao = (SmtpDao) context.getBean("smtpDao");
        map.put("result", smtpDao.getSmtp(param));

        return map;
    }

    /**
     * 新增邮件
     */
    @RequestMapping(value = "addSmtp.action")
    @ResponseBody
    public Object addSmtp(@RequestParam Map<String, Object> param,
            @CookieValue(value = "smtpname", required = false) String smtpname)
    {

        Set<String> set = param.keySet();
        Map<String, Object> map = new HashMap<String, Object>();
        ApplicationContext context = ContextLoader
                .getCurrentWebApplicationContext();

        logger.debug("新增邮件信息");
        for (String key : set)
        {
            logger.debug(key + ":" + param.get(key));
        }
        if (param.get("smtpname") != null && param.get("password") != null)
        {

            SmtpDao dao = (SmtpDao) context.getBean("smtpDao");
            List list = (List) dao.getSmtp(param);
            if (list != null && list.size() == 0)
            {
                dao.addSmtp(param);
                map.put("result", "添加成功");
            }
            else
            {
                map.put("result", "添加失败");
            }

        }
        else
        {
            map.put("result", "缺少邮件名或密码");
        }
        return map;
    }

    /**
     * 删除邮件
     */
    @RequestMapping(value = "deleteSmtp.action")
    @ResponseBody
    public Object deleteSmtp(@RequestParam Map<String, Object> param,
            @CookieValue(value = "smtpname", required = false) String smtpname)
    {
        String deleteedSmtpId = (String) param.get("deleteedSmtpId");
        Map<String, Object> map = new HashMap<String, Object>();

        if (deleteedSmtpId != null)
        {
            logger.debug(smtpname + "删除邮件：" + deleteedSmtpId);
        }
        else
        {
            logger.debug("删除邮件名为空");
        }
        return map;
    }

    @RequestMapping(value = "getSmtpInfo.shtm")
    @ResponseBody
    public Object getSmtpInfo(@RequestParam Map<String, Object> param)
    {
        Map<String, Object> dbParam = new HashMap<String, Object>();
        dbParam.put("id", 1);
        Map<String, Object> result;
        SmtpDao smtpDao = (SmtpDao) SpringContextUtil.getBean("smtpDao");
        result = (Map<String, Object>) smtpDao.getSmtpInfo(dbParam);
        return result;
    }

    @RequestMapping(value = "updateSmtp.shtm", method = RequestMethod.POST)
    public Object updateSmtp(@RequestParam Map<String, Object> param)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        SmtpDao smtpDao = (SmtpDao) SpringContextUtil.getBean("smtpDao");
        smtpDao.updateSmtp(param);
        map.put("operator", 1);

        return map;
    }
}
