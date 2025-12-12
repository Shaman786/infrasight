package com.hostpalace.infrasight.modules.rack;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "racks")
public class Rack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name; // "R-101"

    private String location; // "Zone A"
    
    private int maxUnits = 42;

    // --- NEW: The Missing Link ---
    // One Rack has Many Devices. 
    // "mappedBy" tells Hibernate that the Device class owns the foreign key.
    @OneToMany(mappedBy = "rack", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("rack") // Prevents infinite loop (Rack -> Device -> Rack -> Device...)
    private List<Device> devices;
}