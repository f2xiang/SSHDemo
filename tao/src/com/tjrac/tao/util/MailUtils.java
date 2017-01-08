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
 * �ʼ����͵Ĺ�����
 * @author FengXiang
 *
 */
public class MailUtils {
	/**
	 * �����ʼ��ķ���
	 * @param to �ռ���
 	 * @param code  ������
	 */
	public static void sendMail(String to, String code){
		//1 ���session����
		Properties props = new Properties();
		props.setProperty("mail.host", "localhost");
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				//�ٷ��ķ�������
				return new PasswordAuthentication("service@tao.com", "123");
			}
		}) ;
		
		//2����һ�������ʼ��Ķ��� message
		Message message = new MimeMessage(session);
		try {
			//2.1���÷�����
			message.setFrom(new InternetAddress("service@tao.com"));
			//2.2 �ռ���  RecipientType.TO �ռ��� CC ����  BCC ����
			message.setRecipient(RecipientType.TO, new InternetAddress(to));
			//2.3����
			message.setSubject("���԰�С�Ե�����վ��һ���ʼ�");
			//2.4����
			message.setContent("<h1>����һ�⼤���ʼ������������������ɼ���</h1><h3><a href='http://127.0.0.1:8080/tao/user_active.action?code="+code+ " '>http://127.0.0.1:8080/tao/user_active.action?code="+code+"</a></h3>", "text/html;charset=utf-8");
		
			//3�����ʼ�transport
			Transport.send(message);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
}
