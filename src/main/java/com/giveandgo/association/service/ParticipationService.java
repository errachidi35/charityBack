package com.giveandgo.association.service;

import com.giveandgo.association.entities.Participation;
import com.giveandgo.association.repository.ParticipationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipationService {
    private final ParticipationRepository participationRepository;

    public ParticipationService(ParticipationRepository participationRepository) {
        this.participationRepository = participationRepository;
    }

    public Participation createParticipation(Participation participation) {
        return participationRepository.save(participation);
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