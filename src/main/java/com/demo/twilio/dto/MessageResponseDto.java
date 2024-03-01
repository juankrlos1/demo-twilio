package com.demo.twilio.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "DTO for displaying message information.")
public class MessageResponseDto {

    @Schema(description = "Unique identifier of the message.", example = "1")
    private Long id;

    @Schema(description = "The phone number or numbers to which the message was sent, in E.164 format.",
            example = "+1234567890")
    private String to;

    @Schema(description = "The content of the message.", example = "Hello, World!")
    private String message;

    @Schema(description = "Date and time when the message was created.", example = "2023-01-01T12:00:00")
    private String dateTimeCreated;
}
