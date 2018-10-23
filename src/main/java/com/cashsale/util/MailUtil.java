package com.cashsale.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 发送激活邮件
 * @author Sylvia
 * 2018年10月20日
 */
public class MailUtil {
	
	public static void sendMail(String to, String code, String username, String password, String nickname, String currentTime) throws Exception
	{
		//创建连接对象，连接到邮箱服务器
		Properties props = new Properties();
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			//设置发送方的邮箱地址和密码
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("Cashsale0416@cashsale.com","cashsale0416");
			}
		});
		
		//System.out.println("MailUtil下的密码："+password);
		//System.out.println("MailUtil下的验证码："+code);
		
		//创建邮件对象
		Message message = new MimeMessage(session);
		//设置发件人
		message.setFrom(new InternetAddress("Cashsale0416@cashsale.com"));
		//设置收件人
		message.setRecipient(RecipientType.TO, new InternetAddress(to));
		//设置邮件的主题
		message.setSubject("来自“现卖”的激活邮件");
		//设置邮件的正文
		message.setContent("<h1>亲爱的"+nickname+"：</h1>"+"<br><h3>欢迎加入现卖！<br>" + 
				"请点击下面的连接完成注册：<br></h3>" + "<a href='http://localhost:8080/thecashsale/active?code="+code
				+"&username="+username+"&password="+password+"&currentTime="+currentTime+"'>"+"http://localhost:8080"
				+ "/thecashsale/active?code="+code+"</a><br>"+"<h5>如以上连接无法点击，请将其复制到浏览器中打开（请于5"
				+ "分钟内完成验证，逾期需重新注册）</h5>",
				"text/html;charset=UTF-8");
		//发送一封激活邮件
		Transport.send(message);
	}
}