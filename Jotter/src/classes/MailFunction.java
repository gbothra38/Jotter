package classes;

import java.util.*;
import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.*;
//import javax.activation.*;
import javax.mail.Session;
import javax.mail.Transport;

public class MailFunction {
	public static void sendMailForPassword(String recepient, int user_id) {
		Properties properties=new Properties();
		properties.put("mail.smtp.auth","true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		String myEmailString="gbothra98@gmail.com";
		String myPasswordString="Gaurav@1998";
		
		Session session=Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myEmailString, myPasswordString);
				};
			});
		Message message=prepareMessageForPassword(session, myEmailString, recepient,user_id);
		try {
			Transport.send(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	private static Message prepareMessageForPassword(Session session,String myEmailString,String recepient, int user_id) {
		MimeMessage message=new MimeMessage(session);
		String otpString=OTP.generateOTP();
		UserDBFunctions userDBFunctions=new UserDBFunctions(new DBConnector().makeConnection());
		
		if(userDBFunctions.setToken(recepient, otpString)) {
			try {
				message.setFrom(new InternetAddress(myEmailString));
				message.setRecipient(RecipientType.TO,new InternetAddress(recepient));
				message.setSubject("Jotter: Change your password");
				message.setContent(""
						+ "<h1>Jotter: Change your password </h1><br>"
						+ "OTP (One Time password): " + otpString
						+ "<h2><p><a href='http://127.0.0.1:8080/Jotter/otpAndNewPassword.jsp'>Reset Password</a></p></h2>","text/html; charset=utf-8");
				return message;
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			System.out.println("Token not set.");
		}
		
		return null;
	}
	

		
}