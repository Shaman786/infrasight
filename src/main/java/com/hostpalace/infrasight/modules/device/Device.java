package com.hostpalace.infrasight.modules.device;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hostpalace.infrasight.modules.rack.Rack;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "devices")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // --- Identification ---
    @Column(nullable = false)
    private String label;        // "Web-Srv-01"

    private String assetTag;     // "HP-0099"
    private String serialNumber; // "CZJ..."

    // --- Physical Location ---
    private int uPosition;       // 35
    private int uHeight;         // 2

    // --- Hardware Specs ---
    private String model;        // "HP DL360p Gen8"
    private String cpu;          // "2x E5-2660"
    private String ram;          // "64GB"
    private String storage;      // "2x 500GB SSD"

    // --- Networking ---
    private String primaryIp;    // Public IP
    private String managementIp; // iLO IP

    // --- Status & Ownership ---
    @Enumerated(EnumType.STRING)
    private DeviceStatus status;

    private String customerName; // "Sumit Jain"

    // --- Relationships ---
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rack_id")
    @JsonIgnoreProperties("devices") // Stop infinite recursion when printing Device -> Rack
    private Rack rack;
}
