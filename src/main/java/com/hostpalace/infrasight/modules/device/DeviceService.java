package com.hostpalace.infrasight.modules.device;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hostpalace.infrasight.modules.rack.Rack;
import com.hostpalace.infrasight.modules.rack.RackRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeviceService {
    private final DeviceRepository deviceRepository;
    private final RackRepository rackRepository;

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    public List<Device> getDevicesByRack(Long rackId) {
        return deviceRepository.findByRackId(rackId);
    }

    public Device addDeviceToRack(Long rackId, Device device) {
        // 1. Find the Rack first
        Rack rack = rackRepository.findById(rackId)
                .orElseThrow(() -> new RuntimeException("Rack not found"));

        // 2. Assign Rack to Device
        device.setRack(rack);

        // 3. Save
        return deviceRepository.save(device);
    }
}
