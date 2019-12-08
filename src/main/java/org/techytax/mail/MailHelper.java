package org.techytax.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.techytax.domain.Invoice;
import org.techytax.saas.domain.Registration;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

@Component
public class MailHelper {

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${mail.from}")
	private String mailFrom;

	@Value("${mail.host}")
	private String mailHost;

	private void sendMail(String subject, String message, String to) throws Exception {
		SimpleMailMessage msg = new SimpleMailMessage();
		if (to != null) {
			msg.setTo(to);
		} else
			throw new MessagingException("No \"To\" address specified");
		if (subject != null)
			msg.setSubject(subject);
		msg.setReplyTo(mailFrom);
		msg.setSubject(subject);
		msg.setText(message);
		javaMailSender.send(msg);
	}

	public void sendInvoice(String htmlText, Invoice invoice, byte[] invoiceBuf, Registration registration) throws Exception {
		String to = invoice.getProject().getCustomer().getEmailInvoice();
		String bcc = registration.getPersonalData().getEmail();
		String subj = "Factuur " + invoice.getInvoiceNumber();
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setHost(mailHost);
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo(to);
		helper.setFrom(new InternetAddress(registration.getCompanyData().getCompanyName() + " <" + bcc + ">"));
		helper.setText(htmlText);
		helper.setBcc(bcc);
		helper.setSubject(subj);
		DataSource fds = new ByteArrayDataSource(invoiceBuf, "application/pdf");
		helper.addAttachment("factuur.pdf", fds);
		sender.send(message);
	}
}
