package com.hostpalace.infrasight.modules.rack;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class RackService {
    private final RackRepository repository;

    public RackService(RackRepository repository) {
        this.repository = repository;
    }

    public List<Rack> getAllRacks() {
        return repository.findAll();
    }

    public Rack createRack(String name, String location) {
        Rack newRack = new Rack(null, name, location, 42);
        return repository.save(newRack);
    }

}
