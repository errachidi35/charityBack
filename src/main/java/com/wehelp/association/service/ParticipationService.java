package com.wehelp.association.service;

import com.wehelp.association.entities.Participation;
import com.wehelp.association.repository.ParticipationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ParticipationService {
    private final ParticipationRepository participationRepository;

    public ParticipationService(ParticipationRepository participationRepository) {
        this.participationRepository = participationRepository;
    }

    public Participation createParticipation(Participation participation) {
        boolean exists = participationRepository.existsByBenevoleIdAndMissionId(
            participation.getBenevole().getId(),
            participation.getMission().getId()
        );
        if (exists) {
            throw new IllegalArgumentException("Vous participez déjà à cette mission.");
        }
        return participationRepository.save(participation);
    }

public boolean hasParticipated(Long idBenevole, Long idMission) {
    return participationRepository.existsByBenevoleIdAndMissionId(idBenevole, idMission);
}

    public Optional<Participation> getParticipationById(Long id) {
        return participationRepository.findById(id);
    }

    public List<Participation> getParticipationsByBenevole(Long idBenevole) {
        return participationRepository.findByBenevoleId(idBenevole);
    }

    public List<Participation> getParticipationsByMission(Long idMission) {
        return participationRepository.findByMissionId(idMission);
    }

    public List<Participation> getAllParticipations() {
        return participationRepository.findAll();
    }

    public void deleteParticipation(Long id) {
        participationRepository.deleteById(id);
    }
}