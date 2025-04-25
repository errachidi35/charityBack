package com.wehelp.association.service;

import com.wehelp.association.entities.Benevole;
import com.wehelp.association.repository.BenevoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BenevoleService {
    private final BenevoleRepository benevoleRepository;

    public BenevoleService(BenevoleRepository benevoleRepository) {
        this.benevoleRepository = benevoleRepository;
    }

    public Benevole createBenevole(Benevole benevole) {
        return benevoleRepository.save(benevole);
    }

    public Optional<Benevole> getBenevoleById(Long id) {
        return benevoleRepository.findById(id);
    }

    public List<Benevole> getBenevolesByCompetence(String competence) {
        return benevoleRepository.findByCompetencesContaining(competence);
    }

    public List<Benevole> getAllBenevoles() {
        return benevoleRepository.findAll();
    }

    public void deleteBenevole(Long id) {
        benevoleRepository.deleteById(id);
    }
}