package com.jprogrammers.util;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Date;
import java.util.Properties;
import static com.jprogrammers.util.PropsUtil.*;

/**
 * @author Ali Reza Akbarian
 *         created on 27/06/14.
 */
public class EmailUtil {

    public static void sendEmail(String toEmail, String subject, String body){
        try
        {
            Session session = configureSession();
            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress(get(MAIL_SMTP_USERNAME), "hirsamotors"));

            msg.setReplyTo(InternetAddress.parse(get(MAIL_SMTP_USERNAME), false));

            msg.setSubject(subject, "UTF-8");

            msg.setText(body, "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            System.out.println("Message is ready");
            Transport.send(msg);

            System.out.println("EMail Sent Successfully!!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected static Session configureSession() {

        System.out.println("TLSEmail Start");
        Properties props = new Properties();
        props.put("mail.smtp.host", get(MAIL_SMPT_HOST)); //SMTP Host
        props.put("mail.smtp.port", get(MAIL_SMTP_PORT)); //TLS Port
        props.put("mail.smtp.auth", get(MAIL_SMTP_AUTH)); //enable authentication
        props.put("mail.smtp.starttls.enable", get(MAIL_SMTP_STARTTLS_ENABLE)); //enable STARTTLS

        props.put("mail.debug", "true");

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(get(MAIL_SMTP_USERNAME), get(MAIL_SMTP_PASSWORD));
            }
        };

        return Session.getInstance(props, auth);
    }
}
