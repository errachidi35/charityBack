package com.wehelp.association.controller;

import com.wehelp.association.dto.MessageRequest;
import com.wehelp.association.entities.Discussion;
import com.wehelp.association.entities.Message;
import com.wehelp.association.entities.Utilisateur;
import com.wehelp.association.service.DiscussionService;
import com.wehelp.association.service.MessageService;
import com.wehelp.association.service.UtilisateurService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/message")
public class MessageController {
    private final MessageService messageService;

    private final UtilisateurService utilisateurService;

    private final DiscussionService discussionService;

    public MessageController(MessageService messageService, UtilisateurService utilisateurService, DiscussionService discussionService) {
        this.messageService = messageService;
        this.utilisateurService = utilisateurService;
        this.discussionService = discussionService;
    }

    @PostMapping("/send")
    public Message createMessage(@RequestBody MessageRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Utilisateur utilisateur = utilisateurService.findByEmail(email);
        Discussion discussion = discussionService.getDiscussionById(request.getIdDiscussion())
                .orElseThrow(() -> new RuntimeException("Discussion n'est pas trouvée"));
        Message message = new Message(utilisateur, discussion, request.getContenu());
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

    @GetMapping("/all")
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
        return ResponseEntity.ok().build();
    }
}