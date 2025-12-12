package com.hostpalace.infrasight.modules.rack;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class RackService {
    
    private final RackRepository repository;

    public List<Rack> getAllRacks() {
        return repository.findAll();
    }

    // --- NEW: Needed for the Rack View Page ---
    public Rack getRackById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rack not found with ID: " + id));
    }

    public Rack createRack(String name, String location) {
        // We pass 'null' for the list of devices initially
        Rack newRack = new Rack(null, name, location, 42, null);
        return repository.save(newRack);
    }
}
