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
import cn.shop.base.util.Default;
import cn.shop.base.util.SpringContextUtil;
import cn.shop.dao.GoodsCatDao;
import cn.shop.dao.GoodsDao;
import cn.shop.dao.MemberDao;
import cn.shop.dao.ProductDao;
import cn.shop.model.Check;
import cn.shop.model.MemberInfo;
import cn.shop.model.Page;

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
         Map<Integer, Boolean> haveChild = new HashMap<Integer, Boolean>();
        
         List<Map<String, Object>> list;
         GoodsCatDao goodsCatDao = (GoodsCatDao) SpringContextUtil
         .getBean("goodsCatDao");
        
        list = goodsCatDao.geAlltGoodsCat();
        for (Map<String, Object> temp : list)
        {
            int id = (Integer) temp.get("cat_id");

            for (Map<String, Object> item : list)
            {
                if ((Integer) item.get("parent_id") == id)
                {
                    haveChild.put(id, true);
                }
            }
        }
        for (Map<String, Object> temp : list)
        {
            int id = (Integer) temp.get("cat_id");
            Boolean b = haveChild.get(id);

            b = Default.get(b, false);
            temp.put("haveChild", b);
        }
        model.addAttribute("catList", list);
        return "mall/v2/index.jsp";
    }

    /**
     * 搜索产品
     * @param model
     * @param param
     * @param session
     * @return
     */
    @RequestMapping(value = "search_cat.shtm", method = { RequestMethod.GET })
    public String search(ModelMap model,
            @RequestParam Map<String, String> param, HttpSession session)
    {
        int pageSize = NumberUtils.toInt(param.get("pageSize"), -1);
        int pageNumber = NumberUtils.toInt(param.get("pageNumber"), -1);
        int catId = NumberUtils.toInt(param.get("catId"), -1);
        int totalPage;
        List<Map<String, Object>> goodsList = null;
        List<Map<String, Object>> productList = null;
        List<Map<String, Object>> productFirstList = new ArrayList<Map<String, Object>>();
        List<Integer> goodsIdList = new ArrayList<Integer>();
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
        
        dbParam.put("start", Page.getStartNum(pageSize, pageNumber));
        dbParam.put("num", pageSize);
        // 已经指定分类id
        if (catId > 0)
        {
            List<Integer> typeIds = new ArrayList<Integer>();
            List<Integer> catIds = new ArrayList<Integer>();
            List<Map<String, Object>> result;
            Map<String, Object> daoParam = new HashMap<String, Object>();
            int count = 0;// 限制最大循环次数，防止陷入死循环
            catIds.add(catId);
            daoParam.put("parentIds", catIds);
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
//        按照goods_id,搜索产品，
        if(goodsList != null && goodsList.size() > 0)
        {
            for(Map<String, Object> temp : goodsList)
            {
                goodsIdList.add((Integer)temp.get("goods_id"));
            }
            if(!goodsIdList.isEmpty())
            {
                Map<String, Object> dbParam3 = new HashMap<String, Object>();
                ProductDao productDao = (ProductDao)SpringContextUtil.getBean("productDao");
                dbParam3.put("goodsIds", goodsIdList);
                productList = productDao.getProduct(dbParam3);
                
                if(productList != null && !productList.isEmpty())
                {
                    for(Integer goodsId : goodsIdList)
                    {
                        for(Map<String, Object> temp : productList)
                        {
                          if(goodsId.equals(temp.get("goods_id")))
                          {
                              productFirstList.add(temp);
                              break; // 取出首先发现的产品后不再搜索，
                          }                          
                        }
                    }                    
                }
                
            }
        }
        totalPage = Math.round(total/pageSize);
        model.addAttribute("goodsList", goodsList);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("total", total);
        model.addAttribute("productList", productList);
        model.addAttribute("productFirstList", productFirstList);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("totalPage", totalPage);
        return "mall/v2/search_cat.jsp";
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
    
    /**
     * 商品详情页。
     * 
     * @param model
     * @param param
     * @param session
     * @return
     */
    @RequestMapping(value = "goods.shtm")
    public String goods(ModelMap model,
            @RequestParam Map<String, String> param, HttpSession session)
    {
        int goodsId = NumberUtils.toInt(param.get("goodsId"), -1);
        GoodsDao goodsDao;
        ProductDao productDao;
        Map<String, Object> goodsDetail;
        Map<String, Object> dbParam = new HashMap<String, Object>();
        List<Map<String, Object>> productList;
        List <Integer> goodsIdList = new ArrayList<Integer>();
        
        if(goodsId < 0)
        {
            return "redirect:/mall/search.shtm";
        }
        goodsIdList.add(goodsId);
        dbParam.put("goodsId", goodsId);
        goodsDao = (GoodsDao)SpringContextUtil.getBean("goodsDao");
        productDao = (ProductDao)SpringContextUtil.getBean("productDao");
        goodsDetail = goodsDao.getOneGoodsByGoodsId(dbParam);
        dbParam.put("goodsIds", goodsIdList);
        productList = productDao.getProduct(dbParam);
        if(productList == null || productList.isEmpty())
        {
            return "redirect:/mall/search.shtm";
        }
        model.addAttribute("goodsDetail", goodsDetail);
        model.addAttribute("productList", productList);
       return "mall/v2/goods.jsp";
    }
    
    /**
     * 购物车页面
     * @param model
     * @param param
     * @param session
     * @return
     */
    @RequestMapping(value = "cart.shtm", method={RequestMethod.POST,RequestMethod.GET})
    public String cart(ModelMap model,
            @RequestParam Map<String, String> param, HttpSession session)
    {
        Integer productId = NumberUtils.toInt(param.get("productId"), -1);
        
        if(!Check.memberInline(session))
        {
            return Check.getMemberLoginUrl();
        }
        if(productId < 0)
        {
            logger.info("缺少参数");
            return "redirect:/mall/index.shtm";
        }
        logger.debug("session:" + session.getId());
        return "mall/v2/cart.jsp";
    }    
}
