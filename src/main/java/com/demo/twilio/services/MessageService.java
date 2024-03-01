package com.demo.twilio.services;

import com.demo.twilio.dto.MessageDto;
import com.demo.twilio.dto.MessageResponseDto;
import com.demo.twilio.entities.Message;
import com.demo.twilio.entities.MessageSending;
import com.demo.twilio.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final TwilioService twilioService;
    private final MessageCreationService messageCreationService;


    @Autowired
    public MessageService(MessageRepository messageRepository, TwilioService twilioService, MessageCreationService messageCreationService) {
        this.messageRepository = messageRepository;
        this.twilioService = twilioService;
        this.messageCreationService = messageCreationService;
    }

    public List<MessageSending> processAndSendMessages(MessageDto messageDto) {
        List<MessageSending> list = new ArrayList<>();
        for (String to : messageDto.getTo()) {
            Message message = messageCreationService.createMessage(to, messageDto.getMessage());
            MessageSending messageSending = twilioService.sendMessage(message);
            list.add(messageSending);
        }
        return list;
    }

    @Transactional(readOnly = true)
    public List<MessageResponseDto> getAllMessages() {
        List<Message> messages;
        messages = messageRepository.findAll();
        return messages.stream()
                .map(this::convertToDto)
                .toList();
    }

    private MessageResponseDto convertToDto(Message message) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        return new MessageResponseDto(
                message.getId(),
                message.getToField(),
                message.getMessage(),
                formatter.format(message.getDateTimeCreated())
        );
    }

    public void deleteMessageById(Long id) {
        messageRepository.deleteById(id);
    }
}
