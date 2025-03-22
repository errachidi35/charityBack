package com.giveandgo.association.repository;

import com.giveandgo.association.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}