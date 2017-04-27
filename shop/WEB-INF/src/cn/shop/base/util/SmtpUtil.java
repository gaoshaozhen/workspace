package cn.shop.base.util;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.context.ContextLoader;

import cn.shop.dao.SmtpDao;

public class SmtpUtil
{
    Logger logger = Logger.getLogger(this.getClass());

    static SmtpUtil smtpUtil = null;
    String host;
    String username;
    String password;
    String mail_from;
    String mailSubjectModel = "shop";
    int mailSmtpTimeout = 25000;
    boolean mailSmtpAuth = true;

    public SmtpUtil()
    {
        init();
    }

    /**
     * 获取单例
     * 
     * @return
     */
    public static SmtpUtil getInstance()
    {
        synchronized (SmtpUtil.class)
        {
            if (smtpUtil != null)
            {
                smtpUtil = new SmtpUtil();
                smtpUtil.init();
            }
        }

        return smtpUtil;
    }

    /**
     * 初始化变量
     */
    private void init()
    {
        ApplicationContext context = ContextLoader
                .getCurrentWebApplicationContext();
        SmtpDao dao = (SmtpDao) context.getBean("smtpDao");
        Map<String, Object> map = dao.getDefaultSmtp();

        this.host = (String) map.get("host");
        this.username = (String) map.get("username");
        this.password = (String) map.get("password");
        this.mail_from = (String) map.get("mail_from");
    }

    /**
     * @param content
     * @throws IOException
     * @throws MessagingException
     */
    public void sendEmail(String[] toUser, String content)
    {
        Properties prop = new Properties();
        MimeMessageHelper messageHelper;
        JavaMailSenderImpl senderImpl;
        MimeMessage mailMessage;

        prop.put("mail.smtp.auth", this.mailSmtpAuth); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
        prop.put("mail.smtp.timeout", this.mailSmtpTimeout);

        senderImpl = new JavaMailSenderImpl();
        senderImpl.setHost(this.host);
        senderImpl.setUsername(this.username);
        senderImpl.setPassword(this.password);
        senderImpl.setJavaMailProperties(prop);
        mailMessage = senderImpl.createMimeMessage();
        try
        {
            // 注意这里的boolean,等于真的时候才能嵌套图片，在构建MimeMessageHelper时候，所给定的值是true表示启用，
            // multipart模式
            messageHelper = new MimeMessageHelper(mailMessage, true);
            messageHelper.setTo(toUser);
            messageHelper.setFrom(username);
            messageHelper.setSubject(mailSubjectModel);
            // // true 表示启动HTML格式的邮件
            messageHelper.setText(content, true);
            logger.debug("开始发送邮件");
            senderImpl.send(mailMessage);
            logger.debug("邮件发送成功..");
        }
        catch (Exception e)
        {
            logger.error("邮件发送失败");
        }
    }
}
