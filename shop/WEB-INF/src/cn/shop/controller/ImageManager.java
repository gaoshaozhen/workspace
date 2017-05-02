package cn.shop.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cn.shop.base.Configuration;
import cn.shop.base.util.ClassLoaderUtil;
import cn.shop.base.util.SpringContextUtil;

@Controller
@RequestMapping(value="/imageManager/")
public class ImageManager
{
    static Logger logger = Logger.getLogger(ImageManager.class);
    @RequestMapping(value="image.shtm")
    public Object getImage(@RequestParam Map<String, String>param, HttpServletResponse response)
    {
        OutputStream out = null;
        String fileId = param.get("fileId");
        Configuration conf = (Configuration)SpringContextUtil.getBean("configuration");
        String dir = ClassLoaderUtil.getRootPath()+ conf.get("fileDir", "");
        File file;
        FileInputStream in = null;
        if(fileId == null)
        {
            logger.info("缺少参数");
            return null;
        }
        file = new File(dir + fileId);
        if(!file.exists())
        {
            
            logger.info("文件不存在" + file.getPath());
            return null;
        }
        try
        {
            byte [] b = new byte[1024];
            in = new FileInputStream(file);
            out = response.getOutputStream();
            while(in.read(b) > 0)
            {
                out.write(b);
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                in.close();
                out.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            
        }
        return null;
    }
    public static String uploadFile(MultipartFile file, HttpServletRequest request) throws IOException {  
//        String fileName = file.getOriginalFilename();
//        
////        File tempFile = new File(FILE_PATH, new Date().getTime() + String.valueOf(fileName));  
//        if (!tempFile.getParentFile().exists()) {  
//            tempFile.getParentFile().mkdir();  
//        }  
//        if (!tempFile.exists()) {  
//            tempFile.createNewFile();  
//        }  
//        file.transferTo(tempFile);  
//        return "/download?fileName=" + tempFile.getName();
        return null;
    }  
}
