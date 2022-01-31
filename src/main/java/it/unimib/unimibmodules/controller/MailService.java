package it.unimib.unimibmodules.controller;

/**
 * Service for sending emails.
 * @author Davide Costantini
 * @version 1.0.0
 */
public interface MailService {

	/**
	 * Send email to the specified addreess, with specified subject and text.
	 * @param	recipientAddress	the recipient's email addrss
	 * @param	subject				the subject of the email
	 * @param	text				the text of the email
	 */
	void sendMail(String recipientAddress, String subject, String text);
}
