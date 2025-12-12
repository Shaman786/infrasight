package com.hostpalace.infrasight.modules.device;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    // Custom query: "SELECT * FROM device WHERE rack_id = ?"
    List<Device> findByRackId(Long rackId);
}