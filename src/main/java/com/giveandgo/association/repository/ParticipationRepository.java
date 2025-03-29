package com.giveandgo.association.repository;

import com.giveandgo.association.entities.Participation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {
    List<Participation> findByBenevoleId(Long idBenevole);
    List<Participation> findByMissionId(Long idMission);
}