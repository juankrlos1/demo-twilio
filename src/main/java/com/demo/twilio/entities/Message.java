package com.demo.twilio.entities;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Getter
@Setter
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "date_time_created")
    private LocalDateTime dateTimeCreated;

    @Column(name = "to_field")
    private String toField;

    @Column(name = "message")
    private String message;
}
