package cn.shop.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import cn.shop.base.util.FileManager;
import cn.shop.base.util.SpringContextUtil;
import cn.shop.dao.BrandDao;

/**
 * 品牌处理类。
 * 
 * @author shaozhen
 */
@Controller
@RequestMapping(value = "/brand/")
public class BrandController
{
    static Logger logger = Logger.getLogger(BrandController.class);

    /**
     * 获得品牌列表
     * 
     * @return
     */
    @RequestMapping(value = "getBrand.action")
    @ResponseBody
    public Object getBrand(@RequestParam Map<String, Object> param)
    {
        logger.debug(param.toString());
        ApplicationContext context = ContextLoader
                .getCurrentWebApplicationContext();
        BrandDao dao = (BrandDao) context.getBean("brandDao");

        return dao.getBrand(param);
    }

    /**
     * 新增品牌
     */
    @RequestMapping(value = "addBrand.action")
    @ResponseBody
    public Object addBrand(@RequestParam Map<String, Object> param,
            HttpServletRequest request) throws IllegalStateException,
            IOException
    {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> dbParam = new HashMap<String, Object>();
        String fileName = null;
        List<?> list;
        logger.debug(param.toString());        
        map.put("name", param.get("name"));
        BrandDao dao = (BrandDao) SpringContextUtil.getBean("brandDao");
//        list = (List<?>) dao.getBrand(map);
        dbParam.put("name", param.get("name"));
        dbParam.put("url", param.get("url"));
        dbParam.put("brief", param.get("brief"));
        // 判断 request 是否有文件上传,即多部分请求
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
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
                        fileName = (String) fileMap.get("fileName");
//                        vector.add((String) fileMap.get("fileName"));
                        file.transferTo((File) fileMap.get("file"));
                    }
                }
            }
        }        
        dbParam.put("logo", fileName);
        dao.addBrand(dbParam);
        result.put("operator", true);
//        if (list != null && list.size() == 0)
//        {
//            dao.addBrand(param);
//            result.put("operator", true);
//        }
//        else
//        {
//            result.put("operator", false);
//        }

        return result;
    }

    /**
     * 删除品牌
     */
    @RequestMapping(value = "deleteBrand.action")
    @ResponseBody
    public Object deleteBrand(@RequestParam Map<String, Object> param)
    {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> dbParam = new HashMap<String, Object>();
        ApplicationContext context = ContextLoader
                .getCurrentWebApplicationContext();
        BrandDao dao = (BrandDao) context.getBean("brandDao");
        dbParam.put("brand_ids", param.get("brand_ids"));
        dao.deleteBrand(dbParam);
        result.put("result", true);
        return result;
    }
    
    @RequestMapping(value = "updateBrand.shtm")
    @ResponseBody
    public Object updateBrand(@RequestParam Map<String, Object> param)
    {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> dbParam = new HashMap<String, Object>();        
        BrandDao dao = (BrandDao) SpringContextUtil.getBean("brandDao");
        dbParam.put("name", param.get("name"));
        dbParam.put("url", param.get("url"));
        dbParam.put("brief", param.get("brief"));
        
        dbParam.put("brandId", param.get("brandId"));
        
        dao.updateBrand(dbParam);
        result.put("result", true);
        return result;
    }
}