package se.inyat.emailsender.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.inyat.emailsender.domain.dto.EmailDTO;
import se.inyat.emailsender.domain.entity.Email;
import se.inyat.emailsender.repository.EmailRepository;

@Service
public class EmailServiceImpl implements EmailService {

    private final EmailRepository emailRepository;

    @Autowired
    public EmailServiceImpl(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    @Override
    public void sendEmail(EmailDTO dto) {
        if(dto == null) throw new IllegalArgumentException("")     ;
        //convert dto to entity
        Email emailEntity = Email.builder()
                .to(dto.getTo())
                .from("test@inyat.se")
                .subject(dto.getSubject())
                .content(dto.getHtml())
                .type(dto.getType())
                .build();
                emailRepository.save(emailEntity);

                //todo send email to user

    }
}
