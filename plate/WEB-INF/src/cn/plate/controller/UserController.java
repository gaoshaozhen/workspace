package cn.plate.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.gsz.tools.ContextTool;
import cn.plate.javaBean.UserBean;
import cn.plate.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController
{
    private Logger log = Logger.getLogger(UserController.class);

    /**
     * @param userBean
     * @param response
     */
    @RequestMapping(value = "/createUser")
    public void createUser(UserBean userBean, HttpServletResponse response)
    {
        PrintWriter w = null;
        try
        {
            w = response.getWriter();
            UserService userService = ContextTool.getBean("userService",
                    UserService.class);
            if (userBean.getUserId() == null && userBean.getPassword() == null)
            {
                w.println(false);
            }
            else
            {
                w.print(userService.addUser(userBean));
            }
        }
        catch (IOException e)
        {
            log.error(e.getLocalizedMessage());
        } finally
        {
            if (w != null)
            {
                w.close();
            }
        }
    }
}
