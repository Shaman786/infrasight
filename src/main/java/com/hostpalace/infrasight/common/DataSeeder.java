package com.hostpalace.infrasight.common;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.hostpalace.infrasight.modules.device.Device;
import com.hostpalace.infrasight.modules.device.DeviceRepository;
import com.hostpalace.infrasight.modules.device.DeviceStatus;
import com.hostpalace.infrasight.modules.rack.Rack;
import com.hostpalace.infrasight.modules.rack.RackRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {
    private final RackRepository rackRepo;
    private final DeviceRepository deviceRepo;

    @Override
    public void run(String... args) throws Exception {
        // Only load data if DB is empty
        if (rackRepo.count() == 0) {
            System.out.println("--- Seeding HostPalace Data ---");

            // 1. Create a Rack (Simulating "HP RACK ELE")
            Rack r1 = new Rack(null, "R-101", "HostPalace HQ", 42, null);
            rackRepo.save(r1);

            // 2. Add Stock Server
            Device d1 = new Device(null, "Stock-Dell-01", "ASSET-992", "SRL-112",
                    30, 2,
                    "Dell R730", "2x E5-2680v3", "128GB", "2x 1TB SSD",
                    "192.168.1.10", "10.0.0.5",
                    DeviceStatus.AVAILABLE, null, r1);

            // 3. Add Customer Server (Simulating "Sumit Jain")
            Device d2 = new Device(null, "Sumit-App-Srv", "ASSET-881", "SRL-998",
                    25, 1,
                    "HP DL360 Gen9", "2x E5-2660v4", "64GB", "500GB NVMe",
                    "203.0.113.55", "10.0.0.12",
                    DeviceStatus.ACTIVE, "Sumit Jain", r1);

            deviceRepo.saveAll(Arrays.asList(d1, d2));
            System.out.println("--- Data Loaded Successfully ---");
        }
    }
}
