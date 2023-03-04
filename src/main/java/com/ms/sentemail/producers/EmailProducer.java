package com.ms.sentemail.producers;

import com.ms.sentemail.dtos.EmailDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class EmailProducer {
    @Autowired
    RabbitTemplate rabbitTemplate;
    public void sendEmail(EmailDto emailDto) {
        log.info("Sending message");
        rabbitTemplate.convertAndSend("ms.email", emailDto);
        log.info("sent message ");
    }
}
