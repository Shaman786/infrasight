package com.hostpalace.infrasight.modules.rack;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/racks")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class RackController {
    private final RackService service;

    @GetMapping
    public List<Rack> getRacks() {
        return service.getAllRacks();
    }

    // --- NEW: Get a single rack (and its devices) ---
    @GetMapping("/{id}")
    public ResponseEntity<Rack> getRack(@PathVariable Long id) {
        return ResponseEntity.ok(service.getRackById(id));
    }

    @PostMapping
    public Rack createRack(@RequestBody Rack rack) {
        return service.createRack(rack.getName(), rack.getLocation());
    }
}
