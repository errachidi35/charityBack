package com.wehelp.association.controller;

import com.wehelp.association.entities.Don;
import com.wehelp.association.service.DonService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/don")
public class DonController {
    private final DonService donService;

    public DonController(DonService donService) {
        this.donService = donService;
    }

    @PostMapping("/create")
    public Don createDon(@RequestBody Don don) {
        return donService.createDon(don);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Don> getDonById(@PathVariable Long id) {
        Optional<Don> don = donService.getDonById(id);
        return don.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/mission/{idMission}")
    public List<Don> getDonsByMission(@PathVariable Long idMission) {
        return donService.getDonsByMission(idMission);
    }

    @GetMapping("/all")
    public List<Don> getAllDons() {
        return donService.getAllDons();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteDon(@PathVariable Long id) {
        donService.deleteDon(id);
        return ResponseEntity.ok().build();
    }
}