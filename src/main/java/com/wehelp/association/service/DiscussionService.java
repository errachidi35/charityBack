package com.wehelp.association.service;

import com.wehelp.association.entities.Discussion;
import com.wehelp.association.repository.DiscussionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DiscussionService {
    private final DiscussionRepository discussionRepository;

    public DiscussionService(DiscussionRepository discussionRepository) {
        this.discussionRepository = discussionRepository;
    }

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