package se.inyat.emailsender.service;

import org.springframework.stereotype.Service;
import se.inyat.emailsender.domain.dto.EmailDTO;
import se.inyat.emailsender.domain.entity.Email;

import java.util.List;

//@Service
public interface EmailService {

    //   void sendEmail(String to, String subject, String content, Integer type);
// use EmailDTO insted of this    String sendEmail(String to, String subject, String content, Integer type, List<String> attachments);

    void sendEmail(EmailDTO emailDTO);
}
