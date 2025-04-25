package com.wehelp.association.service;

import com.wehelp.association.entities.CategorieMission;
import com.wehelp.association.entities.Mission;
import com.wehelp.association.repository.MissionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MissionService {
    private final MissionRepository missionRepository;

    public MissionService(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    public Mission createMission(Mission mission) {
        return missionRepository.save(mission);
    }

    public Optional<Mission> getMissionById(Long id) {
        return missionRepository.findById(id);
    }

    public List<Mission> getMissionsByResponsable(Long idResponsable) {
        return missionRepository.findByResponsableId(idResponsable);
    }

    public List<Mission> getMissionsByType(String type) {
        return missionRepository.findByTypeMission(CategorieMission.valueOf(type));
    }

    public List<Mission> getAllMissions() {
        return missionRepository.findAll();
    }

    public void deleteMission(Long id) {
        missionRepository.deleteById(id);
    }
}