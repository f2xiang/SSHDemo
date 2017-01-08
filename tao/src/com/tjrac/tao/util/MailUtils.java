package com.tjrac.tao.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 邮件发送的工具类
 * @author FengXiang
 *
 */
public class MailUtils {
	/**
	 * 发送邮件的方法
	 * @param to 收件人
 	 * @param code  激活码
	 */
	public static void sendMail(String to, String code){
		//1 获得session对象
		Properties props = new Properties();
		props.setProperty("mail.host", "localhost");
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				//官方的发送邮箱
				return new PasswordAuthentication("service@tao.com", "123");
			}
		}) ;
		
		//2创建一个代表邮件的对象 message
		Message message = new MimeMessage(session);
		try {
			//2.1设置发件人
			message.setFrom(new InternetAddress("service@tao.com"));
			//2.2 收件人  RecipientType.TO 收件人 CC 抄送  BCC 密送
			message.setRecipient(RecipientType.TO, new InternetAddress(to));
			//2.3标题
			message.setSubject("来自爱小淘电商网站的一封邮件");
			//2.4正文
			message.setContent("<h1>这是一封激活邮件，请点击下面的链接完成激活</h1><h3><a href='http://127.0.0.1:8080/tao/user_active.action?code="+code+ " '>http://127.0.0.1:8080/tao/user_active.action?code="+code+"</a></h3>", "text/html;charset=utf-8");
		
			//3发送邮件transport
			Transport.send(message);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
}
