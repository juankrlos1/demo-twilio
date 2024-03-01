package com.demo.twilio.controllers;

import com.demo.twilio.dto.MessageDto;
import com.demo.twilio.dto.MessageResponseDto;
import com.demo.twilio.entities.MessageSending;
import com.demo.twilio.services.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/messages")
public class MessagesController {

    private final MessageService messageService;

    @Autowired
    public MessagesController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/send")
    @Operation(summary = "Send a message to one or multiple phone numbers",
            description = "Sends a text message to the provided phone numbers. Each number receives the same message. For trial accounts, all numbers must be verified with Twilio.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Messages sent successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = MessageSending.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid request",
                            content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content)
            })
    public ResponseEntity<List<MessageSending>> sendMessage(@RequestBody MessageDto messageDto) {
        List<MessageSending> messageSending = messageService.processAndSendMessages(messageDto);
        return new ResponseEntity<>(messageSending, HttpStatus.CREATED);

    }

    @Operation(summary = "Get all sent messages",
            description = "Retrieves a list of all messages that have been sent.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of messages successfully retrieved",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = MessageResponseDto.class)))),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            })
    @GetMapping
    public ResponseEntity<List<MessageResponseDto>> getAllMessages() {
        List<MessageResponseDto> messageDto = messageService.getAllMessages();
        return new ResponseEntity<>(messageDto, HttpStatus.OK);
    }

    @Operation(summary = "Delete a message by ID",
            description = "Deletes a specific message based on the provided ID.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Message successfully deleted"),
                    @ApiResponse(responseCode = "404", description = "Message not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
        messageService.deleteMessageById(id);
        return ResponseEntity.noContent().build();
    }
}
