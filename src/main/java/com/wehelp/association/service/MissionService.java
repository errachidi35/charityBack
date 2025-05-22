package com.wehelp.association.service;

import com.wehelp.association.entities.CategorieMission;
import com.wehelp.association.entities.Don;
import com.wehelp.association.entities.Mission;
import com.wehelp.association.repository.MissionRepository;
import com.wehelp.association.repository.DonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MissionService {
    private final MissionRepository missionRepository;
    private final DonRepository donRepository;

    public MissionService(MissionRepository missionRepository, DonRepository donRepository) {
        this.missionRepository = missionRepository;
        this.donRepository = donRepository;
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
    List<Mission> missions = missionRepository.findAll();
    missions.forEach(mission -> {
        double total = donRepository.findByMissionId(mission.getId())
                       .stream()
                       .mapToDouble(Don::getMontant)
                       .sum();
        mission.setRaised(total);
    });
    return missions;
}


    public void deleteMission(Long id) {
        missionRepository.deleteById(id);
    }
}