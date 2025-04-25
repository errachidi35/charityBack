package com.wehelp.association.repository;

import com.wehelp.association.entities.Discussion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscussionRepository extends JpaRepository<Discussion, Long> {
    Discussion findByMissionId(Long idMission);
}