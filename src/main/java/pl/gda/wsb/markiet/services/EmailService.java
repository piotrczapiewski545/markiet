package pl.gda.wsb.markiet.services;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * send email via gmail account, ssl connection
 *
 * @author Piotr Czapiewski
 */
@Service
public class EmailService {
    
    final static private String EMAIL_FROM = "testzyrafy@gmail.com";
    final static private String PASSWORD = "zyrafyzszafy";
    final static private String RESET_EMAIL = "Markiet - password reset";
    final static private String SIGNUP_EMAIL = "Welcome to Markiet";
    
    @Autowired
    StringService stringService;
    
    private void sendEmail(String recipient, String subject, String messageBody){
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(props,
            new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(EMAIL_FROM,PASSWORD);
                }
            });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_FROM));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(messageBody);

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void resetPassword(String recipient){
        String message = "Hello,\n"
                + "Your new password is\n"
                + stringService.getNewPassword() + "\n"
                + "Best regards\n"
                + "Markiet team";
        sendEmail(recipient, RESET_EMAIL, message);
    }
    
    public void signUp(String recipient){
        String message = "Hello,\n"
                + "Your account has been created.\n"
                + "Best regards\n"
                + "Markiet team";
        sendEmail(recipient, SIGNUP_EMAIL, message);
    }

    public String getTestEmail() {
        return EMAIL_FROM;
    }
}
