package com.giveandgo.association.controller;

import com.giveandgo.association.entities.Message;
import com.giveandgo.association.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public Message createMessage(@RequestBody Message message) {
        return messageService.createMessage(message);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable Long id) {
        Optional<Message> message = messageService.getMessageById(id);
        return message.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/discussion/{idDiscussion}")
    public List<Message> getMessagesByDiscussion(@PathVariable Long idDiscussion) {
        return messageService.getMessagesByDiscussion(idDiscussion);
    }

    @GetMapping("/utilisateur/{idUtilisateur}")
    public List<Message> getMessagesByUtilisateur(@PathVariable Long idUtilisateur) {
        return messageService.getMessagesByUtilisateur(idUtilisateur);
    }

    @GetMapping
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
        return ResponseEntity.ok().build();
    }
}