package ru.musailov.Project2SpringBoot.test;


import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;

import java.util.Properties;

public class EmailServiceTest {
    private final Properties props = new Properties();
    private final String username = "zhuzhik250@mail.ru";
    private final String password =
            "Y6kfkYecemkN8bLFNs4H";
    public EmailServiceTest() {
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.mail.ru");
        props.put("mail.smtp.port", "587");


    }
    @Test
    public void sendEmail() throws MessagingException {
        String subject = "something";
        String text = "Some text";
        Session session = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        PasswordAuthentication passwordAuthentication = new PasswordAuthentication(username, password);
                        return passwordAuthentication;
                    }
                });
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("zhuzhik250@mail.ru"));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("zhuzhik250@mail.ru"));
        message.setSubject(subject);
        message.setText(text);

        Transport.send(message);

        System.out.println("Message sent successfully ...");
    }
}
