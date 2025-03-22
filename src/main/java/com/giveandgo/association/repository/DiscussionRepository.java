package com.giveandgo.association.repository;

import com.giveandgo.association.model.Discussion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscussionRepository extends JpaRepository<Discussion, Long> {
    Discussion findByMissionId(Long idMission);
}