package test;

import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;


public class Test
{
    
    public static void main(String[] args)    
    {
          long x = 100;   
          Date date = new Date(x); 
          String str = DateFormatUtils.format(date,"HH:mm:ss");
          
          
          System.out.println(new Boolean("true"));       
    }  
}
