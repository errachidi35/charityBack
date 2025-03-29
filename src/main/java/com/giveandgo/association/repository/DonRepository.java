package com.giveandgo.association.repository;

import com.giveandgo.association.entities.Don;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DonRepository extends JpaRepository<Don, Long> {
    List<Don> findByMissionId(Long idMission);
}