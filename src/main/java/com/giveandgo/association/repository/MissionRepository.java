package com.giveandgo.association.repository;

import com.giveandgo.association.entities.Mission;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    List<Mission> findByResponsableId(Long idResponsable);
    List<Mission> findByTypeName(String type);
}