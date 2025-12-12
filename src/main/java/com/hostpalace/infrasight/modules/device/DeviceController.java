package com.hostpalace.infrasight.modules.device;

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
@RequestMapping("/api/v1/devices")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class DeviceController {
    private final DeviceService service;

    @GetMapping
    public List<Device> getAll() {
        return service.getAllDevices();
    }

    // Example: /api/v1/devices/rack/1 -> Gets all servers in Rack 1
    @GetMapping("/rack/{rackId}")
    public List<Device> getByRack(@PathVariable Long rackId) {
        return service.getDevicesByRack(rackId);
    }

    // Example: POST /api/v1/devices/rack/1
    @PostMapping("/rack/{rackId}")
    public ResponseEntity<Device> addDevice(@PathVariable Long rackId, @RequestBody Device device) {
        return ResponseEntity.ok(service.addDeviceToRack(rackId, device));
    }
}
