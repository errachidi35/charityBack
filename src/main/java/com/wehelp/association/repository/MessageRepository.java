package com.wehelp.association.repository;

import com.wehelp.association.entities.Message;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByDiscussionId(Long idDiscussion);
    List<Message> findByUtilisateurId(Long idUtilisateur);
}