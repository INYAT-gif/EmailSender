package se.inyat.emailsender.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.inyat.emailsender.domain.entity.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, String> {


}
