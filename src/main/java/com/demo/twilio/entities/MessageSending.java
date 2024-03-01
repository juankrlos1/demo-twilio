package com.demo.twilio.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "message_sending")
@Getter
@Setter
public class MessageSending {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id")
    private Message message;

    @CreatedDate
    @Column(name = "date_time_sent", nullable = false)
    private LocalDateTime dateTimeSent;

    @Column(name = "confirmation_code", nullable = false)
    private String confirmationCode;
}