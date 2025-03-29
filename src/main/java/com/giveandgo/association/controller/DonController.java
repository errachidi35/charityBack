package com.giveandgo.association.controller;

import com.giveandgo.association.entities.Don;
import com.giveandgo.association.service.DonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dons")
public class DonController {
    @Autowired
    private DonService donService;

    @PostMapping
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

    @GetMapping
    public List<Don> getAllDons() {
        return donService.getAllDons();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDon(@PathVariable Long id) {
        donService.deleteDon(id);
        return ResponseEntity.ok().build();
    }
}