package com.demo.twilio.repositories;

import com.demo.twilio.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByDateTimeCreatedBetween(LocalDateTime start, LocalDateTime end);
    List<Message> findByToFieldContaining(String to);

}