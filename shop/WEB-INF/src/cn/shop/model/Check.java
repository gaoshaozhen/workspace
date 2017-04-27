package cn.shop.model;

import javax.servlet.http.HttpSession;

/**
 * 登录安全检查。
 * 
 * @author shaozhen
 */
public class Check
{
    /**
     * 在线则session中含有memberInfo对象。
     */
    final static String identity = "memberInfo";

    /**
     * 登录url。
     */
    final static String memberLoginUrl = "redirect:/mall/login.shtm";

    /**
     * 检查会员是否登录。
     * 
     * @param session
     * @return
     */
    public static boolean memberInline(HttpSession session)
    {
        if (session.getAttribute(identity) == null || session.isNew())
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * 获取会员登录url。
     * 
     * @return
     */
    public static String getMemberLoginUrl()
    {
        return memberLoginUrl;
    }

    /**
     * 获取会员身份信息类。
     */

    public static MemberInfo getMemberInfo(HttpSession session)
    {
        return (MemberInfo) session.getAttribute(identity);
    }
}
