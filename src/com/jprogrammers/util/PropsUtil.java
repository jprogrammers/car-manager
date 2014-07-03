package com.jprogrammers.util;

import com.jprogrammers.language.UTF8Control;

import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * @author Ali Reza Akbarian
 *         created on 27/06/14.
 */
public class PropsUtil {


    public static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
    public static final String MAIL_SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";
    public static final String MAIL_SMPT_HOST = "mail.smtp.host";
    public static final String MAIL_SMTP_PORT = "mail.smtp.port";
    public static final String MAIL_SMTP_USERNAME = "mail.smtp.username";
    public static final String MAIL_SMTP_PASSWORD = "mail.smtp.password";


    private static Properties properties = new Properties();

    static {
        try {
            properties.load(PropsUtil.class.getResourceAsStream("/project.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key){
        return (String) properties.get(key);
    }
}
