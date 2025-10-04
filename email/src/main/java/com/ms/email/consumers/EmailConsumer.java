package com.ms.email.consumers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.email.dtos.EmailRecordDto;
import com.ms.email.models.EmailModel;
import com.ms.email.services.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    final EmailService emailService;

    public EmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "${broker.queue.email.name}")
    public void listenEmailQueue(String message) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            EmailRecordDto emailRecordDto = mapper.readValue(message, EmailRecordDto.class);

            System.out.println("Mensagem convertida: " + emailRecordDto);
            System.out.println("Para quem mandar o email: "+emailRecordDto.emailTO());

            var emailModel = new EmailModel();
            BeanUtils.copyProperties(emailRecordDto, emailModel);
            emailModel.setEmailTo(emailRecordDto.emailTO());

            emailService.sendEmail(emailModel);

        } catch (Exception e) {
            System.out.println("Erro ao converter mensagem: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /*public void listenEmailQueue(@Payload String message){
        System.out.println("Recebendo msg: "+message);
    }*/

}