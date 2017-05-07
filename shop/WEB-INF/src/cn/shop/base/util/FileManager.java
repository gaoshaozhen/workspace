package cn.shop.base.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import cn.shop.base.Configuration;

/**
 * 管理文件。
 * @author shaozhen
 *
 */
public class FileManager
{
    public static Map<String, Object> getTempTile()
    {
        Map<String, Object> map = new HashMap<String, Object>();
        Configuration conf = (Configuration)SpringContextUtil.getBean("configuration");
        String dir = ClassLoaderUtil.getRootPath() + conf.get("fileDir");
        String fileName = createFileName();
        File file = new File(dir + fileName);
        map.put("fileName", fileName);
        map.put("file", file);
        
        return map;
    }
    public static File createTempFile()
    {
        Configuration conf = (Configuration)SpringContextUtil.getBean("configuration");
        String dir = ClassLoaderUtil.getRootPath() + conf.get("fileDir");
        String fileName = createFileName();
        File file = new File(dir + fileName);
        
        return file;
    }
    
    private static String createFileName()
    {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmSS");        
        StringBuilder build = new StringBuilder(format.format(date));
        Random random = new Random();
        
        for(int i = 0; i < 5; i++)
        {
            build.append(random.nextInt(9));
        }
        build.append(".png");
        return build.toString(); 
    }
}
