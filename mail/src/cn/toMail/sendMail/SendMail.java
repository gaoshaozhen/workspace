package cn.toMail.sendMail;
import java.util.*;
import java.io.*;
import javax.mail.*;
import javax.mail.util.*;
import javax.mail.internet.*;
public class SendMail
{
	MailInfo mailInfo;
	String sendUser ;
    String sendPwd  ;
    String mailServerHost ;
    class pass extends Authenticator {
    @Override
    public  PasswordAuthentication getPasswordAuthentication()
      {
        return  new PasswordAuthentication(sendUser,sendPwd);
      } 
	}
    //配置邮件服务器所需信息
  	public void initInfo()throws IOException
  	{
  		Properties props=new Properties();
  		File confFile=new File(new File(SendMail.class.getResource("/").getPath()).getParent()+"\\conf\\config.properties");
  		props.load(new FileInputStream(confFile));
  		this.sendUser=props.getProperty("sendUser");
  		this.sendPwd=props.getProperty("sendPwd");
  		this.mailServerHost=props.getProperty("mailServerHost");
  	}    
	public void sendingMail(MailInfo mailInfo)throws AddressException,
            MessagingException,IOException
	{
		initInfo();
		this.mailInfo=mailInfo;

        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", "smtp.qq.com");
        props.put("mail.smtp.ssl.enable", "true");
        Session session = Session.getDefaultInstance(props,new pass());
 
        // -- Create a new message --
        session.setDebug(true);
        Message msg = new MimeMessage(session);
        // -- Set the FROM and TO fields --
        msg.setFrom(new InternetAddress(sendUser));
        String t[]=mailInfo.getToAddress();
        int len=t.length;
        Address toAddress[]=new Address[len];;
        for(int i=0;i<len;i++)
        {
        	toAddress[i]=new InternetAddress(t[i]);

        }
        msg.setRecipients(Message.RecipientType.TO, toAddress);
        msg.setSubject(mailInfo.getTitle());
        msg.setText(mailInfo.getContent());

        msg.setSentDate(new Date());
        // smtp验证，就是你用来发邮件的邮箱用户名密码
        Transport transport = session.getTransport("smtp");
        transport.connect(mailServerHost, sendUser, sendPwd);
        // 发送
        transport.sendMessage(msg, msg.getAllRecipients());
        transport.close();
	}
	public static void main(String[]args)
	{
		MailInfo mail=new MailInfo();
		mail.setToAddress(new String[]{"2086427989@qq.com","13789355752@163.com"});
		mail.setTitle("空标题");
		mail.setContent("as内容");
		try{
			new SendMail().sendingMail(mail);	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
