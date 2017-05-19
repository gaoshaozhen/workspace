package cn.shop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @RequestMapping(value = "getAllUser.shtm")
    @ResponseBody
    public Object getUser(@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
            @RequestParam(value = "name", required=false) String name)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> dbParam = new HashMap<String, Object>();
        int start = Page.getStartNum(pageSize, pageNumber);
        int num = pageSize;
        name = StringUtils.trimToNull(name);
        dbParam.put("start", start);
        dbParam.put("num", num);
        dbParam.put("name", name);
        UserDao userDao = (UserDao) SpringContextUtil.getBean("userDao");        
        map.put("data",userDao.getAllUser(dbParam));
        map.put("code", 1);
        return map;
    }

    /**
     * 新增用户
     */
    @AuthCheck(login=true, json=true, loginTarge = "")
    @RequestMapping(value = "addUser.shtm")
    @ResponseBody
    public Object addUser(@RequestParam Map<String, Object> param,
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password,
            @RequestParam(value = "mobile", required = false) String mobile,
            @RequestParam(value = "email", required = false) String email)
    {
     
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> user = new HashMap<String, Object>();
        UserDao userDao = (UserDao)SpringContextUtil.getBean("userDao");
        user.put("username", username);
        user.put("password", password);
        user.put("mobile", mobile);
        user.put("email", email);
        userDao.addUser(user);
        map.put("code", 1);
        return map;
    }

    @AuthCheck(login=true, json=true, loginTarge = "")
    @RequestMapping(value = "updateUser.shtm")
    @ResponseBody
    public Object updateUser(
            @RequestParam (value = "id", required = true) String id,
            @RequestParam (value = "username", required = false) String username,
            @RequestParam (value = "password", required = false) String password,
            @RequestParam (value = "email", required = false) String email,
            @RequestParam (value = "mobile", required = false) String mobile)
    {        
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> user = new HashMap<String, Object>();
        UserDao userDao = (UserDao)SpringContextUtil.getBean("userDao");
        user.put("id", id);
        user.put("username", username);
        user.put("password", password);
        user.put("mobile", mobile);
        user.put("email", email);
        userDao.updateUser(user);
        map.put("code", 1);
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
