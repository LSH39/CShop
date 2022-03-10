package member.controller;


import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {


	public String mailSend(String email) {
		boolean result = false;
		// 랜덤코드 생성
		Random r = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			// 숫자/대문자/소문자 랜덤배치를 위한 랜덤수
			int flag = r.nextInt(3);
			if (flag == 0) {
				// 숫자
				int randomNum = r.nextInt(10);
				sb.append(randomNum);
			} else if (flag == 1) {
				// 대문자
				char randomChar = (char) (r.nextInt(26) + 65);
				sb.append(randomChar);
			} else if (flag == 2) {
				// 소문자
				char randomChar = (char) (r.nextInt(26) + 97);
				sb.append(randomChar);
			}
		}
		System.out.println("생성한 랜덤코드 : " + sb.toString());

		// 이메일 설정
		Properties prop = System.getProperties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", 587); // 변경
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", true); // 추가
		prop.put("mail.smtp.ssl.protocols", "TLSv1.2"); // 추가
		// prop.put("mail.smtp.ssl.enable", true); // 주석
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		// 인증정보설정(gmail 로그인)
		Session session = Session.getDefaultInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				PasswordAuthentication pa = new PasswordAuthentication("connectshop91@gmail.com", "team5!!A");
				return pa;
			}
		});
		// 이메일을 작성해서 전송하는 객체 생성
		MimeMessage msg = new MimeMessage(session);

		try {
			msg.setSentDate(new Date()); // 메일 전송날짜 설정
			// 보내는사람 정보
			msg.setFrom(new InternetAddress("connectshop91@gmail.com", "ConnectShop"));
			// 받는사람정보
			InternetAddress to = new InternetAddress(email);
			msg.setRecipient(Message.RecipientType.TO, to);
			// 이메일 제목설정
			msg.setSubject("[ConnectShop] 이메일 인증을 위한 인증번호를 안내드립니다.", "UTF-8");
			// 이메일 내용설정
			msg.setContent("<h1>안녕하세요. ConnectShop입니다.</h1>"+"<h3>인증번호는["+sb.toString()+"] 입니다.</h3>", "text/html;charset=UTF-8");
			// 이메일 전송
			Transport.send(msg);
			result = true;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(result) {
			return sb.toString();
		}else {
			return null;
		}
		
	}
}
