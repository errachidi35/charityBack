package com.wehelp.association.repository;

import com.wehelp.association.entities.CategorieMission;
import com.wehelp.association.entities.Mission;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    List<Mission> findByResponsableId(Long idResponsable);
    List<Mission> findByTypeMission(CategorieMission type);
}