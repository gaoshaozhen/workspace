package cn.shop.base.email;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.context.ContextLoader;

import cn.shop.dao.SmtpDao;

public class SmtpUtil
{
    static SmtpUtil smtpUtil = null;
    String username;
    String password;
    String mailSmtpServer;
    String mailSubjectModel = "测试";
    int mailSmtpTimeout = 25000;
    boolean mailSmtpAuth = true;

    private SmtpUtil()
    {
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
        List<?> list = (List<?>) dao.getSmtp(new HashMap<String, Object>());
        Map<?, ?> map = (Map<?, ?>) list.get(0);

        this.username = (String) map.get("username");
        this.password = (String) map.get("password");
        this.mailSmtpServer = (String) map.get("host");
    }

    /**
     * @param content
     * @throws IOException
     * @throws MessagingException
     */
    public void sendEmail(String[] toUser, String content) throws IOException,
            MessagingException
    {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", mailSmtpAuth);
        // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
        prop.put("mail.smtp.timeout", mailSmtpTimeout);

        JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
        MimeMessage mailMessage = senderImpl.createMimeMessage();
        // 注意这里的boolean,等于真的时候才能嵌套图片，在构建MimeMessageHelper时候，所给定的值是true表示启用，
        // multipart模式
        MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,
                true);
        senderImpl.setHost(mailSmtpServer);
        senderImpl.setUsername(username);
        senderImpl.setPassword(password);
        senderImpl.setJavaMailProperties(prop);
        messageHelper.setTo(toUser);
        messageHelper.setFrom(username);
        messageHelper.setSubject(mailSubjectModel);
        // // true 表示启动HTML格式的邮件
        messageHelper.setText(content, true);
        senderImpl.send(mailMessage);
        System.out.println("邮件发送成功..");
    }
}
