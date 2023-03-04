package com.ms.sentemail.controllers;

import com.ms.sentemail.dtos.EmailDto;
import com.ms.sentemail.models.EmailModel;
import com.ms.sentemail.producers.EmailProducer;
import com.ms.sentemail.services.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    EmailService emailService;

    @Autowired
    EmailProducer emailProducer;

    @PostMapping("sending-email")
    public ResponseEntity<EmailModel> sendingEmail(@RequestBody @Valid EmailDto emailDto){
        EmailModel emailModel = new EmailModel();
        BeanUtils.copyProperties(emailDto, emailModel);
        emailService.sendEmail(emailModel);
        return new ResponseEntity<>(emailModel, HttpStatus.CREATED);
    }

    @PostMapping("send-email")
    public ResponseEntity<HttpStatus> sendEmail(@RequestBody @Valid EmailDto emailDto){
        emailProducer.sendEmail(emailDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
