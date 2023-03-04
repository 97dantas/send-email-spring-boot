package com.ms.sentemail.services;

import com.ms.sentemail.enums.StatusEmail;
import com.ms.sentemail.models.EmailModel;
import com.ms.sentemail.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    JavaMailSender emailSender;

    public EmailModel sendEmail(EmailModel emailModel) {
        emailModel.setSendDateTime(LocalDateTime.now());

        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(emailModel.getEmailFrom());
            mailMessage.setSubject(emailModel.getSubject());
            mailMessage.setTo(emailModel.getEmailTo());
            mailMessage.setText(emailModel.getText());
            emailSender.send(mailMessage);
        } catch (MailException e) {
            emailModel.setStatusEmail(StatusEmail.ERROR);
        } finally {
            emailModel.setStatusEmail(StatusEmail.SENT);
            return emailRepository.save(emailModel);
        }
    }
}
