package it.unimib.unimibmodules.service;

import it.unimib.unimibmodules.controller.MailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Service for sending emails.
 * @author Davide Costantini
 * @version 1.0.0
 */
@Component("mailServiceImpl")
public class MailServiceImpl implements MailService {

	private static final Logger logger = LogManager.getLogger(MailServiceImpl.class);

	/**
	 * Send email to the specified addreess, with specified subject and text.
	 * @param	recipientAddress	the recipient's email addrss
	 * @param	subject				the subject of the email
	 * @param	text				the text of the email
	 */
	@Override
	public void sendMail(String recipientAddress, String subject, String text) {

		final String username = "EMAIL_USERNAME@gmail.com";
		final String password = "EMAIL_PASSWORD";

		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", "EMAIL_SMTP_HOST");
		properties.put("mail.smtp.auth", "EMAIL_SMTP_AUTH");
		properties.put("mail.smtp.port", "EMAIL_SMTP_PORT");
		properties.put("mail.smtp.starttls.enable", "EMAIL_SMTP_TLS");

		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientAddress));
			message.setSubject(subject);
			message.setContent(text, "text/html");

			new Thread(() -> {
				try {
					Transport.send(message);
					logger.debug("Email sent.");
				} catch (MessagingException e) {
					logger.error("MessagingException: error sending email.");
				}
			}).start();
		} catch (MessagingException e) {
			logger.error("MessagingException: error sending email.");
		}
	}
}
