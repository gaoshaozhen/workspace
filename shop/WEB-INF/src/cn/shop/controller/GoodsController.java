package cn.shop.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
            HttpServletRequest request) throws IllegalStateException, IOException 
    {
        
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //判断 request 是否有文件上传,即多部分请求
        if(multipartResolver.isMultipart(request)){
            //转换成多部分request  
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
            //取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();            
            while(iter.hasNext()){
                //记录上传过程起始时的时间，用来计算上传时间
                int pre = (int) System.currentTimeMillis();
                //取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());               
                if(file != null){
                    //取得当前上传文件的文件名称
                    String myFileName = file.getOriginalFilename();
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在
                    if(myFileName.trim() !=""){
                        System.out.println(myFileName);
                        //重命名上传后的文件名
                        String fileName = "demoUpload" + file.getOriginalFilename();
                        //定义上传路径
                        String path = "d:/" + fileName;
                        File localFile = new File(path);
                        file.transferTo(localFile);
                    }
                }
                //记录上传该文件后的时间
                int finaltime = (int) System.currentTimeMillis();
                System.out.println(finaltime - pre);
            }
            
        }
        
        int typeId;
        TypeDao typeDao;
        GoodsDao goodsDao;
        ProductDao productDao;
        Map<String, Object> dbParam = new HashMap<String, Object>();
        Map<String, Object> goodsParam = new HashMap<String, Object>();
        Map<String, Object> productParam = new HashMap<String, Object>();
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> type = new HashMap<String, Object>();
        String sn = IdManager.createSn();
       
        int goodsId = IdManager.createGoodsId();
        int productId = IdManager.createProductId();
        goodsDao = (GoodsDao) SpringContextUtil.getBean("goodsDao");
        typeDao = (TypeDao) SpringContextUtil.getBean("typeDao");
        productDao = (ProductDao) SpringContextUtil.getBean("productDao");
        type = typeDao.getTypeByTypeId(dbParam);
        goodsParam.put("name", param.get("name"));
        goodsParam.put("sn", sn);
        if (Default.get((Integer) type.get("join_brand"), -1) > 0)
        {
            goodsParam.put("brandId", param.get("brandId"));
        }
        if (Default.get((Integer) type.get("have_param"), -1) > 0)
        {
            goodsParam.put("params", param.get("params"));
        }
        if (Default.get((Integer) type.get("have_prop"), -1) > 0)
        {
            goodsParam.put("props", param.get("props"));
        }
        goodsParam.put("goodsId", goodsId);
        goodsParam.put("catId", NumberUtils.toInt(param.get("catId").toString()));
        goodsParam.put("typeId", param.get("typeId"));
        goodsParam.put("marketEnable", param.get("marketEnable"));
        goodsParam.put("brief", param.get("brief"));
        goodsParam.put("intro", param.get("intro"));
        goodsParam.put("price", NumberUtils.toInt(param.get("price").toString(), 0));
        goodsParam.put("mktPrice", param.get("mktPrice"));
        goodsParam.put("createTime", System.currentTimeMillis());
        goodsParam.put("disabled", 1);
        goodsParam.put("point", 0);
        goodsParam.put("goodsType", "normal");

        // 产品参数
        productParam.put("productId", productId);
        productParam.put("goodsId", goodsId);
        productParam.put("name", param.get("name"));
        productParam.put("sn", sn);
        productParam.put("store", 0);
        productParam.put("price",
                NumberUtils.toDouble(param.get("price").toString(), 0.0d));
        productParam.put("cost", NumberUtils.toDouble(param.get("cost").toString(), 0.0d));
        productParam.put("weight",
                NumberUtils.toDouble(param.get("weight").toString(), 0.0d));
        goodsDao.addGoods(goodsParam);
        productDao.addProduct(productParam);
        result.put("code", 0);
        return result;
    }
}

