package com.wehelp.association.repository;

import com.wehelp.association.entities.Benevole;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BenevoleRepository extends JpaRepository<Benevole, Long> {
    List<Benevole> findByCompetencesContaining(String competence);
}