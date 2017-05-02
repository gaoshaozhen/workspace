package cn.shop.base.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import cn.shop.base.Configuration;

/**
 * 管理文件。
 * @author shaozhen
 *
 */
public class FileManager
{
    public Object createTempFile()
    {
        Configuration conf = (Configuration)SpringContextUtil.getBean("configuration");
        String dir = ClassLoaderUtil.getRootPath() + conf.get("fileDir");
        String fileName = createFileName();
        File file = new File(dir + fileName);
        
        return file;
    }
    
    public String createFileName()
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
