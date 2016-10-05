package cn.toMail.sendMail;

public class MailInfo
{
	String toAddress[];//接收地址
	String title;//邮件标题
	String content;//邮件正文
	public void setToAddress(String[]toAddress)
	{
		this.toAddress=toAddress;
	}
	public String[] getToAddress()
	{
		return this.toAddress;
	}

	public void setTitle(String title)
	{
		this.title=title;
	}
	public String getTitle()
	{
		return this.title;
	}

	public void setContent(String content)
	{
		this.content=content;
	}
	public String getContent()
	{
		return this.content;
	}
}