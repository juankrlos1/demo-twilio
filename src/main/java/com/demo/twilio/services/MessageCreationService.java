package com.demo.twilio.services;

import com.demo.twilio.entities.Message;
import com.demo.twilio.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class MessageCreationService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageCreationService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Transactional
    public Message createMessage(String to, String messageText) {
        Message message = new Message();
        message.setToField(to);
        message.setMessage(messageText);
        message.setDateTimeCreated(LocalDateTime.now());
        return messageRepository.save(message);
    }
}
