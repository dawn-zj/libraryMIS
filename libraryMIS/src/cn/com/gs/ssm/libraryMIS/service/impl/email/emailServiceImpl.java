package cn.com.gs.ssm.libraryMIS.service.impl.email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import cn.com.gs.common.define.Constants;
import cn.com.gs.ssm.libraryMIS.service.IEmailService;

@Service
public class emailServiceImpl implements IEmailService{
	public static void main(String[] args) throws Exception {
		new emailServiceImpl().sendEmail("", "测试主题", "测试正文");
	}
	@Override
	public void sendEmail(String receiveMailAccount, String subject, String content) throws Exception {
		/**
		 * 1.接收收件人邮件各参数
		 * 2.指定发件人邮箱账户和密码
		 * 3.设置邮件传输协议、邮件主机、是否开启密码安全校验
		 */
		
		String myEmailAccount = Constants.emailAccount;
		String myEmailAuthzPassword = Constants.emailAuthzPassword;
		
		Properties props = new Properties();
		props.put("mail.host", "smtp.qq.com");//邮箱发送主机
		props.put("mail.transport.protocol", "smtp");//邮件传输协议
		//props.put("mail.smtp.auth", true);//开启密码安全校验
		
		//创建一个邮箱
		Session session = Session.getInstance(props);
		MimeMessage ms = new MimeMessage(session);
		//设置邮箱发件人
		InternetAddress address = new InternetAddress(myEmailAccount);
		ms.setFrom(address);
		//设置接收者、主题、正文
		ms.setRecipient(Message.RecipientType.TO, new InternetAddress(receiveMailAccount));
		ms.setSubject(subject);
		ms.setContent(content,"text/html;charset=UTF-8");
		
		//发送
		Transport transport = session.getTransport();
		transport.connect(myEmailAccount, myEmailAuthzPassword);
		transport.sendMessage(ms, ms.getAllRecipients());
		transport.close();
		
	}
	
	public void createMail(Session session){
		
	}

}
