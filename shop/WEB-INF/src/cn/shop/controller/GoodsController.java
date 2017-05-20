package cn.shop.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import cn.shop.annotation.AuthCheck;
import cn.shop.base.util.FileManager;
import cn.shop.base.util.SpringContextUtil;
import cn.shop.dao.GoodsDao;
import cn.shop.model.Page;
import cn.shop.service.GoodsManager;

/**
 * 商品分类处理
 * 
 * @author shaozhen
 */
@Controller
@RequestMapping(value = "/mall/")
public class GoodsController
{
    Logger logger = Logger.getLogger(GoodsController.class);

    @AuthCheck(loginTarge = "", json = true, login = true)
    @RequestMapping(value = "addGoods.shtm", method = RequestMethod.POST)
    @ResponseBody
    public Object getGoodsCatList(@RequestParam Map<String, String> param,
            HttpServletRequest request) throws IllegalStateException,
            IOException
    {
        GoodsManager goodsManager = new GoodsManager();
        Map<String, Object> goods = new HashMap<String, Object>();
        Map<String, Object> result = new HashMap<String, Object>();
        double price = NumberUtils.toDouble(param.get("price"), 0.0d);// 销售价
        double cost = NumberUtils.toDouble(param.get("cost"), 0.0d);// 成本价
        double mktPrice = NumberUtils.toDouble(param.get("mktPrice"), 0.0d);// 市场价
        double weight = NumberUtils.toDouble(param.get("weight"), 0.0d);// 重量
        Vector<String> vector = new Vector<String>();
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        // 判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request))
        {
            // 转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext())
            {
                // 记录上传过程起始时的时间，用来计算上传时间
                int pre = (int) System.currentTimeMillis();
                // 取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                if (file != null)
                {
                    // 取得当前上传文件的文件名称
                    String myFileName = file.getOriginalFilename();
                    // 如果名称不为“”,说明该文件存在，否则说明该文件不存在
                    if(StringUtils.trimToNull(myFileName) != null)
//                    if (myFileName.trim() != "")
                    {
                        Map<String, Object> fileMap = FileManager.getTempTile();

                        vector.add((String) fileMap.get("fileName"));
                        file.transferTo((File) fileMap.get("file"));
                    }
                }
            }
        }
        if (!vector.isEmpty())
        {
            String vectorStr = vector.toString();
            goods.put("imageDefault", vector.firstElement());
            goods.put("imageFile",vectorStr.replace("[", "").replace("]", ""));
        }
        goods.put("name", param.get("name"));
        goods.put("brandId", param.get("brandId"));
        goods.put("catId", NumberUtils.toInt(param.get("catId"), -1));
        goods.put("typeId", param.get("typeId"));
        goods.put("goodsType", "normal");
        goods.put("unit", param.get("unit"));
        goods.put("weight", weight);
        goods.put("marketEnable", param.get("marketEnable"));
        goods.put("brief", param.get("brief"));
        goods.put("intro", param.get("intro"));
        goods.put("price", price);
        goods.put("cost", cost);
        goods.put("point", NumberUtils.toInt(param.get("point"), 0));
        goods.put("mktPrice", mktPrice);
        goods.put("params", param.get("params"));
        goods.put("haveSpec", NumberUtils.toInt(param.get("haveSpec"), 0));
        goods.put("createTime", System.currentTimeMillis());
        goods.put("disabled", 0);
        goods.put("store", 0);
        goods.put("metaKeywords", param.get("metaKeywords"));
        goods.put("metaDescription", param.get("metaDescription"));
        goodsManager.addGoods(goods);
        result.put("code", 1);
        return result;
    }

    @AuthCheck(loginTarge = "", json = true, login = true)
    @RequestMapping(value = "getGoods.shtm", method = { RequestMethod.GET })
    @ResponseBody
    public Object getGoods(@RequestParam Map<String, String> param)
    {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> dbParam = new HashMap<String, Object>();
        int start, pageNumber, pageSize;
        pageSize = NumberUtils.toInt(param.get("pageSize"), 10);
        pageNumber = NumberUtils.toInt(param.get("pageNumber"), 1);
        start = Page.getStartNum(pageSize, pageNumber);
        dbParam.put("start", start);
        dbParam.put("num", pageSize);
        dbParam.put("name", StringUtils.trimToNull(param.get("name")));
        GoodsDao goodsDao = (GoodsDao) SpringContextUtil.getBean("goodsDao");
        List<Map<String, Object>> goodsList = goodsDao.getGoodsList(dbParam);
        int total = goodsDao.getAllTotal();
        result.put("total", total);
        result.put("data", goodsList);
        result.put("pageNumber", pageNumber);
        return result;
    }

    @AuthCheck(loginTarge = "", json = true, login = true)
    @RequestMapping(value = "updateGoods.shtm", method = { RequestMethod.GET })
    @ResponseBody
    public Object updateGoods(@RequestParam Map<String, String> param)
    {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> goods = new HashMap<String, Object>();
        GoodsDao goodsDao = (GoodsDao) SpringContextUtil.getBean("goodsDao");
        goods.put("goodsId", NumberUtils.toInt(param.get("goodsId"), -1));
        goods.put("name", StringUtils.trimToNull(param.get("name")));
        goods.put("brandId", param.get("brandId"));
        goods.put("catId", NumberUtils.toInt(param.get("catId"), -1));
        goods.put("typeId", param.get("typeId"));
        goods.put("unit", param.get("unit"));
        // goods.put("weight", NumberUtils.toInt("", null));
        goods.put("marketEnable", param.get("marketEnable"));
        goods.put("brief", StringUtils.trimToNull(param.get("brief")));
        goods.put("intro", StringUtils.trimToNull(param.get("intro")));
        // goods.put("price", price);
        // goods.put("cost", cost);
        goods.put("point", NumberUtils.toInt(param.get("point"), 0));
        // goods.put("mktPrice", mktPrice);
        goods.put("params", param.get("params"));
        goods.put("haveSpec", NumberUtils.toInt(param.get("haveSpec"), 0));
        goods.put("disabled", 0);
        goods.put("store", 0);
        goods.put("metaKeywords",
                StringUtils.trimToNull(param.get("metaKeywords")));
        goods.put("metaDescription",
                StringUtils.trimToNull(param.get("metaDescription")));
        goodsDao.update(goods);
        result.put("code", 1);
        return result;
    }

    @AuthCheck(loginTarge = "", json = true, login = true)
    @RequestMapping(value = "updateGoodsBase.shtm", method = { RequestMethod.POST })
    @ResponseBody
    public Object updateGoodsBase(@RequestParam Map<String, String> param)
    {
        Map<String, Object> result = new HashMap<String, Object>();
        GoodsDao goodsDao = (GoodsDao) SpringContextUtil.getBean("goodsDao");
        Map<String, Object> goods = new HashMap<String, Object>();

        goods.put("name", param.get("name"));
        goods.put("goodsId", NumberUtils.toInt(param.get("goodsId"), -1));
        goods.put("mktEnable", NumberUtils.toInt(param.get("mktEnable"), 1));
        goods.put("cost", NumberUtils.toInt(param.get("cost"), 0));
        goods.put("price", NumberUtils.toInt(param.get("price"), 0));
        goods.put("point", NumberUtils.toInt(param.get("point"), 0));
        result.put("code", 1);
        goodsDao.updateBase(goods);
        return result;
    }

    @AuthCheck(loginTarge = "", json = true, login = true)
    @RequestMapping(value = "updateGoodsIntro.shtm", method = { RequestMethod.POST })
    @ResponseBody
    public Object updateGoodsIntro(@RequestParam Map<String, String> param)
    {
        Map<String, Object> result = new HashMap<String, Object>();
        GoodsDao goodsDao = (GoodsDao) SpringContextUtil.getBean("goodsDao");
        Map<String, Object> goods = new HashMap<String, Object>();

        goods.put("goodsId", NumberUtils.toInt(param.get("goodsId"), -1));
        goods.put("intro", param.get("intro"));
        result.put("code", 1);
        goodsDao.updateIntro(goods);
        return result;
    }
}
