package com.wehelp.association.repository;

import com.wehelp.association.entities.Don;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DonRepository extends JpaRepository<Don, Long> {
    List<Don> findByMissionId(Long idMission);
}