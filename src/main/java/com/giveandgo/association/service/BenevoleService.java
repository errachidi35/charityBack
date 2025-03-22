package com.giveandgo.association.service;

import com.giveandgo.association.model.Benevole;
import com.giveandgo.association.repository.BenevoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BenevoleService {
    @Autowired
    private BenevoleRepository benevoleRepository;

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