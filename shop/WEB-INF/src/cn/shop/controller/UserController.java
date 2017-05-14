package cn.shop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;

import cn.shop.annotation.AuthCheck;
import cn.shop.base.util.SpringContextUtil;
import cn.shop.dao.MemberDao;
import cn.shop.dao.UserDao;
import cn.shop.model.Page;

/**
 * 用户处理
 * 
 * @author shaozhen
 */
@Controller
@RequestMapping(value = "/user/")
public class UserController
{
    private static Logger logger = Logger.getLogger(UserController.class);

    /**
     * 获得用户列表
     * 
     * @return
     */
    @RequestMapping(value = "getUser.action")
    @ResponseBody
    public Object getUser(@RequestParam Map<String, Object> param,
            @CookieValue(value = "username", required = false) String username)
    {
        Set<String> set = param.keySet();
        Map<String, Object> map = new HashMap<String, Object>();
        ApplicationContext context = ContextLoader
                .getCurrentWebApplicationContext();

        logger.debug("查询用户信息");
        for (String key : set)
        {
            logger.debug(key + ":" + param.get(key));
        }        
        UserDao userDao = (UserDao) context.getBean("userDao");
        map.put("result", userDao.getUser(param));

        return map;
    }

    /**
     * 新增用户
     */
    @RequestMapping(value = "addUser.action")
    @ResponseBody
    public Object addUser(@RequestParam Map<String, Object> param,
            @CookieValue(value = "username", required = false) String username)
    {

        Set<String> set = param.keySet();
        Map<String, Object> map = new HashMap<String, Object>();
        ApplicationContext context = ContextLoader
                .getCurrentWebApplicationContext();

        logger.debug("新增用户信息");
        for (String key : set)
        {
            logger.debug(key + ":" + param.get(key));
        }
        if (param.get("username") != null && param.get("password") != null)
        {

            UserDao dao = (UserDao) context.getBean("userDao");
            List list = (List) dao.getUser(param);
            if (list != null && list.size() == 0)
            {
                dao.addUser(param);
                map.put("result", "添加成功");
            }
            else
            {
                map.put("result", "添加失败");
            }

        }
        else
        {
            map.put("result", "缺少用户名或密码");
        }
        return map;
    }

    /**
     * 删除用户
     */
    @RequestMapping(value = "deleteUser.action")
    @ResponseBody
    public Object deleteUser(@RequestParam Map<String, Object> param,
            @CookieValue(value = "username", required = false) String username)
    {
        String deleteedUserId = (String) param.get("deleteedUserId");
        Map<String, Object> map = new HashMap<String, Object>();

        if (deleteedUserId != null)
        {
            logger.debug(username + "删除用户：" + deleteedUserId);
        }
        else
        {
            logger.debug("删除用户名为空");
        }
        return map;
    }
    
    @AuthCheck(login=true, json=true, loginTarge = "")
    @RequestMapping(value = "getMember.shtm")
    @ResponseBody
    public Object getmember(
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
            @RequestParam(value = "name", required=false) String name)
    {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> dbParam = new HashMap<String, Object>();
        int start = Page.getStartNum(pageSize, pageNumber);
        MemberDao memberDao = (MemberDao)SpringContextUtil.getBean("memberDao");
        int num = pageSize;
        name = StringUtils.trimToNull(name);
        dbParam.put("start", start);
        dbParam.put("num", num);
        if(name != null)
        {
            dbParam.put("name", "%" + name + "%");
        }
        
        List<Map<String, Object>> list = memberDao.getAllMember(dbParam);        
        long total = memberDao.getAllMemberTotal(dbParam);
        result.put("data", list);
        result.put("total", total);
        result.put("pageNumber", pageNumber);
        result.put("code",1);
        return result;
    }
}
