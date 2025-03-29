package com.giveandgo.association.repository;

import com.giveandgo.association.entities.Membre;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MembreRepository extends JpaRepository<Membre, Long> {
    List<Membre> findByRole(String role);
}