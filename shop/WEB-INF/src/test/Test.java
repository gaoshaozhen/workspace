package test;

import java.util.Date;
import java.util.Vector;

import org.apache.commons.lang.time.DateFormatUtils;


public class Test
{
    
    public static void main(String[] args)    
    {
          long x = 100;   
          Date date = new Date(x); 
          String str = DateFormatUtils.format(date,"HH:mm:ss");
          Vector v = new Vector();
          
          v.add("sd");
          v.add("sad");
          
          System.out.println(v.toString().replace("[",""));       
    }  
}
