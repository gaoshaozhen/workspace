package test;

import java.util.logging.Logger;

public class TestUserType
{
    public static Logger logger = Logger
            .getLogger(TestUserType.class.getName());

    public static void main(String[] args)
    {
        // logger.info("info\n");
        // logger.warning("info\n");
        // for (int i = 0; i < 15; i++)
        // {
        // System.out.println(ValidateCode.createCode(4));
        // }
        Object o = "23";
        // int w = (Integer) o;
        // System.out.println(StringUtils.);

    }

    public static Object getDefault(Object param, Object value)
    {
        return param == null ? value : param;
    }
}
