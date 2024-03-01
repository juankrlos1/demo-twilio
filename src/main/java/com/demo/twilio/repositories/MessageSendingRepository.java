package com.demo.twilio.repositories;

import com.demo.twilio.entities.MessageSending;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageSendingRepository extends JpaRepository<MessageSending, Long> {
    List<MessageSending> findByMessageId(Long messageId);
    MessageSending findByConfirmationCode(String confirmationCode);
}