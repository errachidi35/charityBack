package com.wehelp.association.service;

import com.wehelp.association.dto.DonRequest;
import com.wehelp.association.entities.Don;
import com.wehelp.association.entities.Mission;
import com.wehelp.association.repository.DonRepository;
import com.wehelp.association.repository.MissionRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DonService {
    private final DonRepository donRepository;

    private final MissionRepository missionRepository;

    public DonService(DonRepository donRepository, MissionRepository missionRepository) {
        this.donRepository = donRepository;
        this.missionRepository = missionRepository;
    }

    public Don createDon(Don don) {
         Mission mission = don.getMission();

    // Ajoute le montant au total raised
    mission.setRaised(mission.getRaised() + don.getMontant());

    missionRepository.save(mission);
        return donRepository.save(don);
    }

    public Don createDon(DonRequest donRequest) {
        // Vérifier que la mission existe
        Mission mission = missionRepository.findById(donRequest.getMissionId())
            .orElseThrow(() -> new IllegalArgumentException("Aucune mission trouvée avec ID: " + donRequest.getMissionId()));

        // Créer un nouveau don
        Don don = new Don();
        don.setNomDonateur(donRequest.getNomDonateur());
        don.setMontant(donRequest.getMontant());
        don.setDate(LocalDate.now());
        don.setMission(mission);

        return donRepository.save(don);
    }

    public Optional<Don> getDonById(Long id) {
        return donRepository.findById(id);
    }

    public List<Don> getDonsByMission(Long idMission) {
        return donRepository.findByMissionId(idMission);
    }

    public List<Don> getAllDons() {
        return donRepository.findAll();
    }

    public void deleteDon(Long id) {
        donRepository.deleteById(id);
    }
}