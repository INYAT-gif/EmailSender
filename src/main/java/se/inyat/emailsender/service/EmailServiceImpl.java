package se.inyat.emailsender.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.HandlerExceptionResolver;
import se.inyat.emailsender.config.EmailProperties;
import se.inyat.emailsender.domain.dto.EmailDTO;
import se.inyat.emailsender.domain.entity.Email;
import se.inyat.emailsender.exception.EmailException;
import se.inyat.emailsender.repository.EmailRepository;

@Service
public class EmailServiceImpl implements EmailService {

    private final EmailRepository emailRepository;
    private final EmailProperties emailProperties;
    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailServiceImpl(EmailRepository emailRepository, EmailProperties emailProperties, JavaMailSender javaMailSender) {
        this.emailRepository = emailRepository;
        this.emailProperties = emailProperties;
        this.javaMailSender = javaMailSender;
    }

    @Override
    @Transactional
    public void sendEmail(EmailDTO dto) {
        if (dto == null) throw new IllegalArgumentException("Email is null");

        //how to create method to send email
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            //helper message
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
            mimeMessage.setContent(dto.getHtml(), "text/html");
            helper.setTo(dto.getTo());
            helper.setFrom(emailProperties.getUsername());
            helper.setSubject(dto.getSubject());

            //convert dto to entity
            Email emailEntity = Email.builder()
                    .to(dto.getTo())
                    //   .from("test@inyat.se") remove hardcoded mail and set from EmailProperties
                    .subject(dto.getSubject())
                    .content(dto.getHtml())
                    .type(dto.getType())
                    .build();
            emailRepository.save(emailEntity);

            //send the mail
            javaMailSender.send(mimeMessage);

       } catch (MessagingException e) {
            throw new EmailException("Error sending the email: " + e.getMessage(), e);
        }
    }
}
