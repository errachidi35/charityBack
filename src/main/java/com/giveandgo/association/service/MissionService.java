package com.giveandgo.association.service;

import com.giveandgo.association.entities.Mission;
import com.giveandgo.association.repository.MissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MissionService {
    @Autowired
    private MissionRepository missionRepository;

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
        return missionRepository.findByType(type);
    }

    public List<Mission> getAllMissions() {
        return missionRepository.findAll();
    }

    public void deleteMission(Long id) {
        missionRepository.deleteById(id);
    }
}