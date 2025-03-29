package com.giveandgo.association.service;

import com.giveandgo.association.entities.Discussion;
import com.giveandgo.association.repository.DiscussionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiscussionService {
    @Autowired
    private DiscussionRepository discussionRepository;

    public Discussion createDiscussion(Discussion discussion) {
        return discussionRepository.save(discussion);
    }

    public Optional<Discussion> getDiscussionById(Long id) {
        return discussionRepository.findById(id);
    }

    public Discussion getDiscussionByMission(Long idMission) {
        return discussionRepository.findByMissionId(idMission);
    }

    public List<Discussion> getAllDiscussions() {
        return discussionRepository.findAll();
    }

    public void deleteDiscussion(Long id) {
        discussionRepository.deleteById(id);
    }
}