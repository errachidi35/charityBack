package com.giveandgo.association.service;

import com.giveandgo.association.dto.DonRequest;
import com.giveandgo.association.entities.Don;
import com.giveandgo.association.entities.Mission;
import com.giveandgo.association.repository.DonRepository;
import com.giveandgo.association.repository.MissionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DonService {
    @Autowired
    private DonRepository donRepository;

    @Autowired
    private MissionRepository missionRepository;

    public Don createDon(Don don) {
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