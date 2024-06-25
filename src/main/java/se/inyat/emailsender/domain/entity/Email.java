package se.inyat.emailsender.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "_from", nullable = false)
    private String from;

    @Column(name = "_to", nullable = false)
    private String to;

    private String subject;

    @Lob
    @Column(length = 65000)
    private String content;

    @Lob//bianary data can be any type of data attached
    private List<String> attachments;


    private LocalDateTime dateTime;


    private Integer type;
//add more fields as needed

//one way to initialize dateTime
 //   public Email() {
   //     dateTime = LocalDateTime.now();
    // }
    //but we need to insert dateTime to db right before we send the email
    @PrePersist//execute method right before persisting sending email
    public void initiateDate(){
        dateTime = LocalDateTime.now();
    }
}
