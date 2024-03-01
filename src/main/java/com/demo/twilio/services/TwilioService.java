package com.demo.twilio.services;

import com.demo.twilio.entities.Message;
import com.demo.twilio.entities.MessageSending;
import com.demo.twilio.repositories.MessageSendingRepository;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TwilioService {

    private final MessageSendingRepository messageSendingRepository;

    @Value("${twilio.account_sid}")
    private String accountSid;

    @Value("${twilio.auth_token}")
    private String authToken;

    @Value("${twilio.from_phone}")
    private String fromPhone;

    @Autowired
    public TwilioService(MessageSendingRepository messageSendingRepository) {
        this.messageSendingRepository = messageSendingRepository;
    }

    public void initializeTwilio() {
        Twilio.init(accountSid, authToken);
    }

    public MessageSending sendMessage(Message message) {
        initializeTwilio();

        com.twilio.rest.api.v2010.account.Message twilioMessage = com.twilio.rest.api.v2010.account.Message.creator(
                new PhoneNumber(message.getToField()),
                new PhoneNumber(fromPhone),
                message.getMessage()
        ).create();

        MessageSending messageSending = new MessageSending();
        messageSending.setMessage(message);
        messageSending.setDateTimeSent(java.time.LocalDateTime.now());
        messageSending.setConfirmationCode(twilioMessage.getSid());

        return messageSendingRepository.save(messageSending);
    }
}