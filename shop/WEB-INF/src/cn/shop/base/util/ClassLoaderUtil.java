package cn.shop.base.util;

import java.io.File;


public class ClassLoaderUtil
{

    public static String getRootPath()
    {
        String filePath = ClassLoaderUtil.class.getClassLoader()
                .getResource("")
                .getFile();
        File file = new File(filePath).getParentFile().getParentFile();
        String path = file.getPath().replaceAll("\\\\", "/");
        return path + "/";
    }
}
