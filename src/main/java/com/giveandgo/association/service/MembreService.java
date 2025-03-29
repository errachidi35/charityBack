package com.giveandgo.association.service;

import com.giveandgo.association.entities.Membre;
import com.giveandgo.association.repository.MembreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MembreService {
    @Autowired
    private MembreRepository membreRepository;

    public Membre createMembre(Membre membre) {
        return membreRepository.save(membre);
    }

    public Optional<Membre> getMembreById(Long id) {
        return membreRepository.findById(id);
    }

    public List<Membre> getMembresByRole(String role) {
        return membreRepository.findByRole(role);
    }

    public List<Membre> getAllMembres() {
        return membreRepository.findAll();
    }

    public void deleteMembre(Long id) {
        membreRepository.deleteById(id);
    }
}