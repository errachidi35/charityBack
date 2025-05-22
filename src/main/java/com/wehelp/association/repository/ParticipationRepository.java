package com.wehelp.association.repository;

import com.wehelp.association.entities.Participation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {
    List<Participation> findByBenevoleId(Long idBenevole);
    List<Participation> findByMissionId(Long idMission);
    boolean existsByBenevoleIdAndMissionId(Long idBenevole, Long idMission);

}