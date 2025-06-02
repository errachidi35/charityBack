package com.wehelp.association.controller;

import com.wehelp.association.entities.Discussion;
import com.wehelp.association.service.DiscussionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/discussion")
public class DiscussionController {
    private final DiscussionService discussionService;

    public DiscussionController(DiscussionService discussionService) {
        this.discussionService = discussionService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Discussion createDiscussion(@RequestBody Discussion discussion) {
        return discussionService.createDiscussion(discussion);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Discussion> getDiscussionById(@PathVariable Long id) {
        Optional<Discussion> discussion = discussionService.getDiscussionById(id);
        return discussion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/mission/{idMission}")
    public ResponseEntity<Discussion> getDiscussionByMission(@PathVariable Long idMission) {
        Discussion discussion = discussionService.getDiscussionByMission(idMission);
        return discussion != null ? ResponseEntity.ok(discussion) : ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Discussion> getAllDiscussions() {
        return discussionService.getAllDiscussions();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteDiscussion(@PathVariable Long id) {
        discussionService.deleteDiscussion(id);
        return ResponseEntity.ok().build();
    }
}