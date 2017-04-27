package cn.shop.controller.mall;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.shop.base.Configuration;
import cn.shop.base.UserClassification;
import cn.shop.base.notify.Notify;
import cn.shop.base.util.SpringContextUtil;
import cn.shop.dao.GoodsCatDao;
import cn.shop.dao.GoodsDao;
import cn.shop.dao.MemberDao;
import cn.shop.model.MemberInfo;

/**
 * @author shaozhen
 */
@Controller
@RequestMapping(value = "/mall/")
public class MallController
{
    Logger logger = Logger.getLogger(this.getClass());

    /**
     * 获得类型列表
     * 
     * @return
     */
    @RequestMapping(value = "index.shtm", method = RequestMethod.GET)
    public Object getType(ModelMap model,
            @RequestParam Map<String, String> param,
            HttpSession session)
    {
        // Map<Integer, Boolean> haveChild = new HashMap<Integer, Boolean>();
        //
        // List<Map<String, Object>> list;
        // GoodsCatDao goodsCatDao = (GoodsCatDao) SpringContextUtil
        // .getBean("goodsCatDao");
        //
        // list = (List<Map<String, Object>>) goodsCatDao.getGoodsCat();
        // for (Map<String, Object> temp : list)
        // {
        // int id = (Integer) temp.get("cat_id");
        //
        // for (Map<String, Object> item : list)
        // {
        // if ((Integer) item.get("parent_id") == id)
        // {
        // haveChild.put(id, true);
        // }
        // }
        // }
        // for (Map<String, Object> temp : list)
        // {
        // int id = (Integer) temp.get("cat_id");
        // Boolean b = haveChild.get(id);
        //
        // b = Default.get(b, false);
        // temp.put("haveChild", b);
        // }
        // model.addAttribute("catList", list);
        return "mall/v2/index.jsp";
    }

    @RequestMapping(value = "search_cat.shtm", method = { RequestMethod.POST,
            RequestMethod.GET })
    public String search(ModelMap model,
            @RequestParam Map<String, String> param, HttpSession session)
    {
        int pageSize = NumberUtils.toInt(param.get("pageSize"), -1);
        int pageNumber = NumberUtils.toInt(param.get("pageSize"), -1);
        int catId = NumberUtils.toInt(param.get("catId"), -1);

        List<Map<String, Object>> goodsList = null;
        int total = 0;
        Map<String, Object> dbParam = new HashMap<String, Object>();
        GoodsDao goodsDao = (GoodsDao) SpringContextUtil.getBean("goodsDao");
        GoodsCatDao goodsCatDao = (GoodsCatDao) SpringContextUtil
                .getBean("goodsCatDao");

        if (pageSize <= 0)
        {
            pageSize = 20;
        }
        if (pageNumber <= 0)
        {
            pageNumber = 1;
        }
        
        dbParam.put("pageSize", pageSize);
        dbParam.put("pageNumber", pageNumber);
        // 已经指定分类id
        if (catId > 0)
        {
            List<Integer> typeIds = new ArrayList<Integer>();
            List<Integer> catIds = new ArrayList<Integer>();
            List<Map<String, Object>> result;
            Map<String, Object> daoParam = new HashMap<String, Object>();
            int count = 0;
            
            daoParam.put("parent_id", catIds);
            catIds.add(catId);
            do
            {
                result = goodsCatDao.getGoodsCatByParentIds(daoParam);
                catIds.clear();
                if(result.size() > 0)
                {
                    for(Map<String, Object>temp : result)
                    {
                        Integer catIdTemp = (Integer)temp.get("cat_id");  
                        if(!catIds.contains(catIdTemp))// 发现新的下级分类
                        {
                            Integer typeId = (Integer)temp.get("type_id");
                            
                            catIds.add(catIdTemp);
                            if(typeId != null && !typeIds.contains(typeId))//将搜索出的新type_id加入列表
                            {
                                typeIds.add(typeId);
                            }
                        }
                    }
                }
                else
                {
                    break;
                }
                if(count++ > 1000)
                {
                    logger.info("循环次数超出1000次，出现死循环，请程序员检查代码");
                }
            }while(false);
            if (typeIds.size() > 0)
            {
                dbParam.put("typeIds", typeIds);
                total = goodsDao.getGoodsTotalByTypeIds(dbParam);
                goodsList = goodsDao.getGoodsByTypeIds(dbParam);
            }
            // 指定分类下没有搜索出任何商品类型
            else
            {
                total = 0;
                goodsList = null;
            }
        }
        else
        // 没有指定分类id，搜索所有商品
        {
            total = goodsDao.getAllTotal();
            goodsList = goodsDao.getAllGoods(dbParam);
        }
        model.addAttribute("goodsList", goodsList);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("total", total);
        return "mall/v2/search.jsp";
    }

    /**
     * 注册处理。
     * 
     * @param model
     * @param param
     * @return
     */
    @RequestMapping(value = "registerId.shtm", method = { RequestMethod.POST })
    public String registerId(HttpServletRequest request, ModelMap model,
            @RequestParam Map<String, String> param)
    {
        String message;
        Configuration configuration;
        MemberDao memberDao;
        Map<String, Object> dbParam = new HashMap<String, Object>();
        String username = StringUtils.defaultIfEmpty(param.get("username"), "")
                .trim();
        String password = StringUtils.defaultIfEmpty(param.get("password"), "")
                .trim();
        String email = StringUtils.defaultIfEmpty(param.get("email"), "")
                .trim();

        // 检查参数,缺少则调回注册页面
        if (username.length() < 3 || password.length() < 3
                || email.length() < 3)
        {
            return "redirect:/mall/register.shtm";
        }
        // 添加新用户
        dbParam.put("uname", username);
        dbParam.put("password", password);
        dbParam.put("email", email);
        dbParam.put("lv_id", 1);
        memberDao = (MemberDao) SpringContextUtil.getBean("memberDao");
        memberDao.addMember(dbParam);
        // 发送邮件通知
        configuration = (Configuration) SpringContextUtil
                .getBean("configuration");
        message = configuration.get("registerMessage");
        message = String.format(message, username);
        if (!new Notify().sendEmail(message, new String[] { email }))
        {
            logger.info("注册通知消息发送失败");
        }
        request.setAttribute("password", password);
        request.setAttribute("username", username);
        request.setAttribute("userClassification", UserClassification.MEMBER);

        return "forward:/sign/in.shtm";
    }

    /**
     * 注册页面。
     * 
     * @param model
     * @param param
     * @return
     */
    @RequestMapping(value = "register.shtm")
    public String register(ModelMap model,
            @RequestParam Map<String, String> param)
    {
        return "mall/v2/register.jsp";
    }

    /**
     * 登录页面。
     * 
     * @param model
     * @param param
     * @return
     */
    @RequestMapping(value = "login.shtm")
    public String login(HttpServletRequest request, ModelMap model,
            @RequestParam Map<String, String> param)
    {

        return "mall/v2/login.jsp";
    }

    /**
     * 登入会员中心。
     * 
     * @param model
     * @param param
     * @param session
     * @return
     */
    @RequestMapping(value = "member_index.shtm")
    public String index(ModelMap model,
            @RequestParam Map<String, String> param, HttpSession session)
    {
        MemberInfo memberInfo = (MemberInfo) session.getAttribute("memberInfo");

        if (memberInfo == null
                || session.isNew())
        {
            return "redirect:/mall/login.shtm";
        }
        return "mall/v2/member_index.jsp";
    }
}
