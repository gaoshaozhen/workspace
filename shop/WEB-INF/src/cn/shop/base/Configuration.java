package cn.shop.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
        FileInputStream stream;
        
        try
        {
            stream = new FileInputStream(new File(path));
            properties.load(stream);
            stream.close();
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
        String value;

        if (key == null)
        {
            return null;
        }
        value = (String) this.properties.get(key);
        try
        {
            value = new String(value.getBytes(), "utf-8");
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return value;
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
