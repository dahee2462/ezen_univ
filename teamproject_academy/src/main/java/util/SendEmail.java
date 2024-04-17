package util;




import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.RandomStringUtils;

public class SendEmail {
	
	public String sendEmail(String email) {
			
		String from = "project1030ezen@gmail.com";
		String to = email;
		String code = RandomStringUtils.random(5, false, true);
		
		//사용자에게 보낼 메시지
		String subject = "비밀번호 찾기를 위한 이메일 인증 메일입니다.";
		String content = "이메일 인증을 진행해주세요. " 
		        + code;
		
		Properties p = new Properties();
		p.put("mail.smtp.user", from);
		p.put("mail.smtp.host", "smtp.googlemail.com");
		p.put("mail.smtp.port", "465"); //TLS 587, SSL 465
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.debug", "true");
		p.put("mail.smtp.socketFactory.port", "465"); 
		p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		p.put("mail.smtp.sockerFactory.fallback", "false");
		p.put("mail.smtp.ssl.protocols", "TLSv1.2"); // 추가된 코드
		p.put("mail.smtp.ssl.enable", "true");  // 추가된 코드
	
		try {
			Authenticator auth = new Gmail();
			Session ses = Session.getInstance(p, auth);
			ses.setDebug(true);
			MimeMessage msg = new MimeMessage(ses);
			msg.setSubject(subject);
			Address fromAddr = new InternetAddress(from);
			msg.setFrom(fromAddr);
			Address toAddr = new InternetAddress(to);
			msg.addRecipient(Message.RecipientType.TO, toAddr);
			msg.setContent(content, "text/html; charset=UTF8");
			Transport.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return code;
	}
}
