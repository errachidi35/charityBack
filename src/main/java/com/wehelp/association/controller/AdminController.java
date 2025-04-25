package com.wehelp.association.controller;

import com.wehelp.association.dto.MembreRegister;
import com.wehelp.association.entities.Admin;
import com.wehelp.association.entities.Membre;
import com.wehelp.association.service.AdminService;
import com.wehelp.association.service.UtilisateurService;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;

    private final UtilisateurService utilisateurService;

    public AdminController(AdminService adminService, UtilisateurService utilisateurService) {
        this.adminService = adminService;
        this.utilisateurService = utilisateurService;
    }

    @PostMapping("/create")
    public Admin createAdmin(@RequestBody Admin admin) {
        return adminService.createAdmin(admin);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable Long id) {
        Optional<Admin> admin = adminService.getAdminById(id);
        return admin.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public List<Admin> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        return ResponseEntity.ok().build();
    }

//    @PostConstruct
//    void populate() {
//        Admin admin = new Admin("admin@association.com", "$2a$10$xJcBZ6wU8lFD5OSO7UUzF.iFbi6ajGhWFJbGa2u6Jex4P.EP0k2rm", "SAMI", "Ayoub");
//        admin.setAdresse("1 Rue Saunirre, 31000 Toulouse");
//        admin.setTelephone("0612345678");
//        adminService.createAdmin(admin);
//    }


    @PostMapping("/createmembre")
    public ResponseEntity<Membre> createMembre(@Valid @RequestBody MembreRegister request) {
        Membre membre = utilisateurService.createMembreByAdmin(request);
        return ResponseEntity.ok(membre);
    }
}