package se.inyat.emailsender.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

    //how to access the getter method from EmailProperties.java?

    private final EmailProperties emailProperties;

    @Autowired
    public MailConfig(EmailProperties emailProperties) {
        this.emailProperties = emailProperties;
    }

    @Bean
    public JavaMailSender javaMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        //set parameters and config mail server details
        mailSender.setHost(emailProperties.getHost());
        mailSender.setPort(emailProperties.getPort());
        mailSender.setUsername(emailProperties.getUsername());
        mailSender.setPassword(emailProperties.getPassword());
        mailSender.getJavaMailProperties().put("mail.smtp.auth", emailProperties.isSmtpAuth());
        mailSender.getJavaMailProperties().put("mail.smtp.starttls.enable",emailProperties.isStartTls());

        return mailSender;
    }


}
