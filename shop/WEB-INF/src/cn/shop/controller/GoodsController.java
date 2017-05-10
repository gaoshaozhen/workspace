package cn.shop.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

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

import cn.shop.base.IdManager;
import cn.shop.base.util.Default;
import cn.shop.base.util.FileManager;
import cn.shop.base.util.SpringContextUtil;
import cn.shop.dao.GoodsDao;
import cn.shop.dao.ProductDao;
import cn.shop.dao.TypeDao;

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

    @RequestMapping(value = "addGoods.shtm", method = RequestMethod.POST)
    @ResponseBody
    public Object getGoodsCatList(@RequestParam Map<String, String> param,
            HttpServletRequest request) throws IllegalStateException,
            IOException
    {
        TypeDao typeDao;
        GoodsDao goodsDao;
        ProductDao productDao;
        Map<String, Object> dbParam = new HashMap<String, Object>();
        Map<String, Object> goodsParam = new HashMap<String, Object>();
        Map<String, Object> productParam = new HashMap<String, Object>();
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> type = new HashMap<String, Object>();
        String sn = IdManager.createSn();//
        int goodsId = IdManager.createGoodsId();//货物编码
        int productId = IdManager.createProductId();//产品编码
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
                    if (myFileName.trim() != "")
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
            goodsParam.put("imageDefault", vector.firstElement());

            goodsParam.put("imageFile",
                    vectorStr.substring(1, vectorStr.length() + 1));
        }

        goodsDao = (GoodsDao) SpringContextUtil.getBean("goodsDao");
        typeDao = (TypeDao) SpringContextUtil.getBean("typeDao");
        productDao = (ProductDao) SpringContextUtil.getBean("productDao");
        dbParam.put("typeId", NumberUtils.toInt(param.get("typeId"), -1));
        type = typeDao.getTypeByTypeId(dbParam);
        goodsParam.put("name", param.get("name"));
        goodsParam.put("sn", sn);
        if (Default.get((Integer) type.get("join_brand"), -1) > 0)
        {
            goodsParam.put("brandId",
                    NumberUtils.toInt(param.get("brandId"), -1));
        }
        if (Default.get((Integer) type.get("have_parm"), -1) > 0)
        {
            try{
                JSONArray jsonArray = JSONArray.fromObject(param.get("params"));
                goodsParam.put("params", jsonArray.toString());
            }
            catch(Exception e)
            {
                logger.error("json格式错误");
                logger.error(e);
            }
        }
        if (Default.get((Integer) type.get("have_prop"), -1) > 0)
        {
            try{
                JSONArray jsonArray = JSONArray.fromObject(param.get("props"));
                goodsParam.put("props", jsonArray.toString());
            }
            catch(Exception e)
            {
                logger.error("json格式错误");
                logger.error(e);
            }                        
        }
        goodsParam.put("goodsId", goodsId);
        goodsParam.put("catId", NumberUtils.toInt(param.get("catId"), -1));
        goodsParam.put("typeId", param.get("typeId"));
        goodsParam.put("name", param.get("name"));
        goodsParam.put("marketEnable", param.get("marketEnable"));
        goodsParam.put("brief", param.get("brief"));
        goodsParam.put("intro", param.get("intro"));
        goodsParam.put("price", price);
        goodsParam.put("mktPrice", mktPrice);
        goodsParam.put("createTime", System.currentTimeMillis());
        goodsParam.put("disabled", 1);
        goodsParam.put("point", 0);
        goodsParam.put("goodsType", "normal");
        goodsParam.put("cost", cost);

        // 产品参数
        productParam.put("productId", productId);
        productParam.put("goodsId", goodsId);
        productParam.put("name", param.get("name"));
        productParam.put("sn", sn);
        productParam.put("store", 0);
        productParam.put("price", price);
        productParam.put("cost", cost);
        productParam.put("weight", weight);
        
        goodsDao.addGoods(goodsParam);
        productDao.addProduct(productParam);
        result.put("code", 1 );
        return result;
    }
}
