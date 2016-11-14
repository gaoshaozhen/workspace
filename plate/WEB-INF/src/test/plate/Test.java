package test.plate;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Test
{
    private static Logger logger = Logger.getLogger(Test.class);

    public static void main(String[] args)
    {
        PropertyConfigurator.configure("WEB-INF/conf/log4j.properties");
        // Dao dao = (Dao) ContextTool.getBean("dao");
        // List<?> userList = (List<?>) dao.getAllUser();
        //
        // for (Object[] user : (List<Object[]>) userList)
        // {
        // for (Object info : user)
        // {
        // System.out.print(info + "\t");
        // }
        // System.out.println();
        // }
        // UserBean userBean = new UserBean();
        // userBean.setUserId("admin");
        // userBean.setPassword("admin");
        // logger.info(dao.isUser(userBean));

    }
}
