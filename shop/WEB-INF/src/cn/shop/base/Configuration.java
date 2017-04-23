package cn.shop.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Configuration
{
    private String path;
    Properties properties = new Properties();
    static Configuration configuration = null;

    public String getPath()
    {
        return path;
    }

    public void setPath(String path)
    {
        path = ClassLoaderUtil.getRootPath() + path;
        this.path = path;
        init();
    }

    public void init()
    {
        try
        {
            properties.load(new FileInputStream(new File(path)));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public String get(String key)
    {
        if (key == null)
        {
            return null;
        }
        return this.properties.getProperty(key);
    }

    public String get(String key, String defaultValue)
    {
        if (key == null)
        {
            return null;
        }
        return this.properties.getProperty(key, defaultValue);
    }

}
