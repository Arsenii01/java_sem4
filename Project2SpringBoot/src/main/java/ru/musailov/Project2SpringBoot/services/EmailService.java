package ru.musailov.Project2SpringBoot.services;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class EmailService {
    private final Properties props = new Properties();
    private final String username = "zhuzhik250@mail.ru";
    private final String password = "Y6kfkYecemkN8bLFNs4H";
    public EmailService() {
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.mail.ru");
        props.put("mail.smtp.port", "587");


    }
    @Async
    public void sendEmail(String subject, String text) throws MessagingException {
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