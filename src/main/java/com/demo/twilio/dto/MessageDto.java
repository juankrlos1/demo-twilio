package com.demo.twilio.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Schema(description = "Data Transfer Object for sending a message.")
public class MessageDto {

    @Schema(description = "List of recipient phone numbers in E.164 format.",
            example = "[\"+50371360734\", \"+10987654321\"]")
    private List<String> to;

    @Schema(description = "Content of the message to be sent.",
            example = "Hello, this is a test message!")
    private String message;
}
