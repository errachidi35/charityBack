package com.giveandgo.association.service;

import com.giveandgo.association.entities.Message;
import com.giveandgo.association.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message createMessage(Message message) {
        return messageRepository.save(message);
    }

    public Optional<Message> getMessageById(Long id) {
        return messageRepository.findById(id);
    }

    public List<Message> getMessagesByDiscussion(Long idDiscussion) {
        return messageRepository.findByDiscussionId(idDiscussion);
    }

    public List<Message> getMessagesByUtilisateur(Long idUtilisateur) {
        return messageRepository.findByUtilisateurId(idUtilisateur);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }
}