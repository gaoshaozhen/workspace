package test;


public class Test
{
    
    public static void main(String[] args)    
    {
          B b = new B();   
          Object o = b;
           System.out.println(o instanceof A);
           System.out.println(o instanceof B);       
    }  
}

class A {
    
}

class B extends A{
    
}