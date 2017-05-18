package cn.shop.controller.mall;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.shop.base.Configuration;
import cn.shop.base.OrderStatus;
import cn.shop.base.UserClassification;
import cn.shop.base.notify.Notify;
import cn.shop.base.util.Default;
import cn.shop.base.util.SpringContextUtil;
import cn.shop.dao.AddressDao;
import cn.shop.dao.CartDao;
import cn.shop.dao.GoodsCatDao;
import cn.shop.dao.GoodsDao;
import cn.shop.dao.MemberDao;
import cn.shop.dao.OrderDao;
import cn.shop.dao.OrderItemsDao;
import cn.shop.dao.ProductDao;
import cn.shop.model.Check;
import cn.shop.model.MemberInfo;
import cn.shop.model.Page;
import cn.shop.model.Prop;

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
            @RequestParam Map<String, String> param, HttpSession session)
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
     * 搜索产品. 1、按照指定catId搜索下级catId 2、按照搜索出的catId搜索typeId 3、按照typeId搜索goodsId
     * 
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
                if (result.size() > 0)
                {
                    for (Map<String, Object> temp : result)
                    {
                        Integer catIdTemp = (Integer) temp.get("cat_id");
                        if (!catIds.contains(catIdTemp))// 发现新的下级分类
                        {
                            Integer typeId = (Integer) temp.get("type_id");

                            catIds.add(catIdTemp);
                            if (typeId != null && !typeIds.contains(typeId))// 将搜索出的新type_id加入列表
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
                if (count++ > 1000)
                {
                    logger.info("循环次数超出1000次，出现死循环，请程序员检查代码");
                }
            } while (false);
            if (typeIds.size() > 0)
            {
                dbParam.put("typeIds", typeIds);
                total = goodsDao.getTotalGoodsByTypeIds(dbParam);
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
        // 按照goods_id,搜索产品，
        if (goodsList != null && goodsList.size() > 0)
        {
            for (Map<String, Object> temp : goodsList)
            {
                goodsIdList.add((Integer) temp.get("goods_id"));
            }
            if (!goodsIdList.isEmpty())
            {
                Map<String, Object> dbParam3 = new HashMap<String, Object>();
                ProductDao productDao = (ProductDao) SpringContextUtil
                        .getBean("productDao");
                dbParam3.put("goodsIds", goodsIdList);
                productList = productDao.getProduct(dbParam3);

                if (productList != null && !productList.isEmpty())
                {
                    for (Integer goodsId : goodsIdList)
                    {
                        for (Map<String, Object> temp : productList)
                        {
                            if (goodsId.equals(temp.get("goods_id")))
                            {
                                productFirstList.add(temp);
                                break; // 取出首先发现的产品后不再搜索，
                            }
                        }
                    }
                }

            }
        }
        totalPage = Math.round(total / pageSize);
        model.addAttribute("goodsList", goodsList);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("total", total);
        model.addAttribute("productList", productList);
        model.addAttribute("productFirstList", productFirstList);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("catId", catId);
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

        if (memberInfo == null || session.isNew())
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
        List<Integer> goodsIdList = new ArrayList<Integer>();

        if (goodsId < 0)
        {
            return "redirect:/mall/search.shtm";
        }
        goodsIdList.add(goodsId);
        dbParam.put("goodsId", goodsId);
        goodsDao = (GoodsDao) SpringContextUtil.getBean("goodsDao");
        productDao = (ProductDao) SpringContextUtil.getBean("productDao");
        goodsDetail = goodsDao.getOneGoodsByGoodsId(dbParam);
        dbParam.put("goodsIds", goodsIdList);
        productList = productDao.getProduct(dbParam);
        if (productList == null || productList.isEmpty())
        {
            return "redirect:/mall/search.shtm";
        }
        Object object = goodsDetail.get("have_parm");
        if((Integer)goodsDetail.get("have_parm") > 0 && goodsDetail.get("params") != null)
        {
            String params = (String)goodsDetail.get("params");
            JSONArray array = null;
            List<Map<String, Object>> arrayList = new ArrayList<Map<String, Object>>();
            try
            {
                array = JSONArray.fromObject(params);                
            }
            catch(Exception e)
            {
                logger.warn(String.format("转换json对象出错，请检查数据仓库字段格式：goodsId=%s", goodsId));
            }               
            if(array != null)
            {
                array.size();
                for (int i = 0, size = array.size(); i < size; i++)
                {
                    Map<String, Object> map = new HashMap<String, Object>();
                    List<Map<String, Object>> paramListObject = new ArrayList<Map<String, Object>>();
                    
                    JSONObject jsonObject = array.getJSONObject(i);
                    JSONArray paramList = jsonObject.getJSONArray("paramList");
                    map.put("name", jsonObject.get("name"));
                    for(int j = 0, len = paramList.size(); j < len; j++)
                    {
                        JSONObject value = paramList.getJSONObject(j);
                        Map<String, Object> paramMap = new HashMap<String, Object>();
                        paramMap.put("name", value.getString("name"));
                        paramMap.put("value", value.getString("value"));                        
                        paramListObject.add(paramMap);
                    }
                    map.put("paramList", paramListObject);
                    map.put("paramNum", jsonObject.get("paramNum"));
                    arrayList.add(map);
                }
            }
            goodsDetail.put("paramsObject", arrayList);
            goodsDetail.put("propObject", Prop.getList((String)goodsDetail.get("prop")));
        }
        model.addAttribute("goodsDetail", goodsDetail);
        model.addAttribute("productList", productList);
        return "mall/v2/goods.jsp";
    }

    /**
     * 购物车页面
     * 
     * @param model
     * @param param
     * @param session
     * @return
     */
    @RequestMapping(value = "cart.shtm", method = { RequestMethod.POST,
            RequestMethod.GET })
    public String cart(ModelMap model, @RequestParam Map<String, String> param,
            HttpSession session)
    {
        CartDao cartDao;
        Map<String, Object> dbParam;
        if (!Check.memberInline(session))
        {
            return Check.getMemberLoginUrl();
        }
        logger.debug("session:" + session.getId());
        dbParam = new HashMap<String, Object>();
        dbParam.put("sessionId", session.getId());
        cartDao = (CartDao) SpringContextUtil.getBean("cartDao");

        model.addAttribute("cartList", cartDao.getCartBySessionId(dbParam));
        return "mall/v2/cart.jsp";
    }

    /**
     * 添加购物车页面
     * 
     * @param model
     * @param param
     * @param session
     * @return
     */
    @RequestMapping(value = "addCart.shtm", method = { RequestMethod.POST,
            RequestMethod.GET })
    public String addCart(ModelMap model,
            @RequestParam Map<String, String> param, HttpSession session)
    {
        Integer productId = NumberUtils.toInt(param.get("productId"), -1);
        int num = NumberUtils.toInt(param.get("num"), -1);
        Map<String, Object> cartDbParam = new HashMap<String, Object>();
        Map<String, Object> productDbParam = new HashMap<String, Object>();
        ProductDao productDao;
        CartDao cartDao;
        Map<String, Object> productDetail;

        if (!Check.memberInline(session))
        {
            return Check.getMemberLoginUrl();
        }

        if (productId < 0 || num < 0)
        {
            logger.info("缺少参数");
            return "redirect:/mall/index.shtm";
        }
        productDao = (ProductDao) SpringContextUtil.getBean("productDao");
        productDbParam.put("productId", productId);
        productDetail = productDao.getOneProductByPruductId(productDbParam);
        cartDbParam.put("goodsId", productDetail.get("goods_id"));
        cartDbParam.put("num", num);
        cartDbParam.put("productId", productId);
        cartDbParam.put("weight", productDetail.get("weight"));
        cartDbParam.put("sessionId", session.getId());
        cartDbParam.put("name", productDetail.get("name"));
        cartDbParam.put("price", productDetail.get("price"));
        cartDao = (CartDao) SpringContextUtil.getBean("cartDao");
        cartDao.addCart(cartDbParam);
        logger.debug("session:" + session.getId());
        return "redirect:/mall/cart.shtm";
    }

    /**
     * 删除购物车商品
     * 
     * @param model
     * @param param
     * @param session
     * @return
     */
    @RequestMapping(value = "deleteCart.shtm", method = { RequestMethod.POST,
            RequestMethod.GET })
    public String deleteCart(ModelMap model,
            @RequestParam Map<String, String> param, HttpSession session)
    {
        int cartId = NumberUtils.toInt(param.get("cartId"), -1);
        CartDao cartDao;
        Map<String, Object> dbParam;

        if (!Check.memberInline(session))
        {
            return Check.getMemberLoginUrl();
        }
        logger.debug("session:" + session.getId());
        dbParam = new HashMap<String, Object>();
        dbParam.put("cartId", cartId);
        cartDao = (CartDao) SpringContextUtil.getBean("cartDao");
        cartDao.deleteCartByCartId(dbParam);
        return "redirect:/mall/cart.shtm";
    }

    /**
     * 填写收货人信息
     * 
     * @param model
     * @param param
     * @param session
     * @return
     */
    @RequestMapping(value = "checkout.shtm", method = { RequestMethod.POST,
            RequestMethod.GET })
    public String checkOut(ModelMap model,
            @RequestParam Map<String, String> param, HttpSession session)
    {
        AddressDao addressDao;
        Map<String, Object> dbParam;
        Map<String, Object> defAddress;
        String cartIds = param.get("cartIds");

        if (!Check.memberInline(session))
        {
            return Check.getMemberLoginUrl();
        }
        // if(cartIds == null || cartIds.trim().equals(""))
        // {
        // return "redirect:/mall/cart.shtm";
        // }
        logger.debug("session:" + session.getId());
        dbParam = new HashMap<String, Object>();
        dbParam.put("memberId", Check.getMemberInfo(session).getMemberId());
        addressDao = (AddressDao) SpringContextUtil.getBean("addressDao");
        defAddress = addressDao.getDefaultAddressBymemberId(dbParam);
        model.addAttribute("defaultAddress", defAddress);
        model.addAttribute("cartIds", cartIds);
        return "mall/v2/checkout.jsp";
    }

    /**
     * 填写收货人信息
     * 
     * @param model
     * @param param
     * @param session
     * @return
     */
    @RequestMapping(value = "addOrder.shtm", method = { RequestMethod.POST,
            RequestMethod.GET })
    public String addOrder(ModelMap model,
            @RequestParam Map<String, String> param, HttpSession session)
    {
        MemberInfo memberInfo;
        String[] cartIds;
        String zip;
        String email;
        String mobile; 
        String addr;
        OrderDao orderDao;
        CartDao cartDao;
        ProductDao productDao;
        OrderItemsDao orderItemsDao;
        List<Integer> list = new ArrayList<Integer>();
        Map<String, Object> dbParam;
        List<Map<String, Object>> cartList;
        int payMentId;
        int memberId;
        String cartIdsStr;
        
        if (!Check.memberInline(session))
        {
            return Check.getMemberLoginUrl();
        }
        memberInfo = Check.getMemberInfo(session);
        memberId = Check.getMemberInfo(session).getMemberId();
        payMentId = NumberUtils.toInt(param.get("payMentId"), -1);
        mobile = param.get("mobile");
        zip = param.get("zip");
        addr = param.get("zddr");
        email = param.get("email");
        dbParam = new HashMap<String, Object>();
        cartIdsStr = param.get("cartIds");
        cartIds = Default.get(cartIdsStr, "").split(",");
        for(int i = 0, size = cartIds.length; i < size; i++)
        {
            int temp = NumberUtils.toInt(cartIds[i], -1);
            if(temp > 0)
            {
                list.add(temp);
            }            
        }
        dbParam.put("cartIds", list);
        cartDao = (CartDao)SpringContextUtil.getBean("cartDao");
        orderDao = (OrderDao)SpringContextUtil.getBean("orderDao");
        productDao = (ProductDao)SpringContextUtil.getBean("productDao");
        orderItemsDao = (OrderItemsDao)SpringContextUtil.getBean("orderItemsDao");
        cartList = cartDao.getCartByCartId(dbParam);
        for(Map<String, Object> map : cartList)
        {
            Map<String, Object> orderDbParam = new HashMap<String, Object>();
            Map<String, Object> productDbParam = new HashMap<String, Object>();
            Map<String, Object> orderItemsDbParam = new HashMap<String, Object>();
            productDbParam.put("productId", map.get("product_id"));
            Map<String, Object> product = productDao.getOneProductByPruductId(productDbParam);
            int num = NumberUtils.toInt(map.get("num").toString(), 0);
            double price = NumberUtils.toDouble(map.get("price").toString(), 0.0);
            
            orderDbParam.put("memberId", memberId);
            orderDbParam.put("sn", product.get("sn"));
            orderDbParam.put("status", OrderStatus.WAIT.getCode());
            orderDbParam.put("payStatus", 0);
            orderDbParam.put("paymentId", payMentId);            
            orderDbParam.put("paymoney", num * price);
            orderDbParam.put("createTime", System.currentTimeMillis());
            orderDbParam.put("goodsNum", num);
            orderDbParam.put("gainedPoint", map.get("point"));
            orderDbParam.put("shipAddr", addr);
            orderDbParam.put("shipZip", zip);
            orderDbParam.put("shipEmail", email);
            orderDbParam.put("shipMobile", mobile);
            orderDbParam.put("shipName", memberInfo.getUsername());
            orderDbParam.put("goods", product.get("name"));
            orderDao.addOrder(orderDbParam);
            
            orderItemsDbParam.put("memberId", memberId);
            orderItemsDbParam.put("productId", map.get("product_id"));
            orderItemsDbParam.put("goodsId", map.get("product_id"));
//            orderItemsDao.addOrderItems(orderItemsDbParam);
            
        }
        return "mall/v2/addOrder.jsp";
    }
}
