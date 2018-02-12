package com.woneway.utils;



import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 邮件工具类
 */
public class MailUtil {
    /**
     * 发送邮件
     * @param to 给谁发
     * @param text 发送内容
     */
    public static void send_mail(String to,String code) throws MessagingException {
        //创建连接对象 连接到邮件服务器
        Properties properties = new Properties();
        //设置发送邮件的基本参数
        //发送邮件服务器
        properties.put("mail.smtp.host", "smtp.163.com");
        //发送端口
        properties.put("mail.smtp.port", "25");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        //设置发送邮件的账号和密码
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                //两个参数分别是发送邮件的账户和密码
                return new PasswordAuthentication("158505XXXXX@163.com","你的独立密码");
            }
        });
    	String text = "<h1>来自XXXXX网站的激活邮件</h1><p>请点此链接进行激活<a href='http://localhost:8080/MailTest/ActiveServlet?code="+code+"'>http://localhost:8080/MailTest/ActiveServlet?code="+code+"</a></p>";
        //创建邮件对象
        Message message = new MimeMessage(session);
        //设置发件人
        message.setFrom(new InternetAddress("158505XXXXX@163.com"));
        //设置收件人
        message.setRecipient(Message.RecipientType.TO,new InternetAddress(to));
        //设置主题
        message.setSubject("来自XXXXX网站的激活邮件");
        //设置邮件正文  第二个参数是邮件发送的类型
        message.setContent(text,"text/html;charset=UTF-8");
        //发送一封邮件
        Transport.send(message);
    }
}