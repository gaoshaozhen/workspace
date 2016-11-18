package test.plate;

import java.util.List;

import cn.gsz.tools.ContextTool;
import cn.plate.service.UserService;

public class UserServiceTest
{

    public static void main(String[] args)
    {
        UserService userService = null;

        userService = ContextTool.getBean("userService", UserService.class);
        List<Object[]> users = userService.getAllUser();
        for (Object[] user : users)
        {
            for (Object info : user)
            {
                System.out.print(info + "\t");
            }
            System.out.println();
        }
    }
}
