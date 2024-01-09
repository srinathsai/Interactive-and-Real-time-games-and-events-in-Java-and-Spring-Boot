package com.BloodBankSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Entity
@Table(name = "blood_usages")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BloodUsage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "used_at")
    private LocalDate usedAt;

    @Column(name = "qty")
    private int qty;

    @Column(name = "blood_type")
    private String bloodType;

    private Long bloodBagId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getUsedAt() {
        return usedAt;
    }

    public void setUsedAt(LocalDate usedAt) {
        this.usedAt = usedAt;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public Long getBloodBagId() {
        return bloodBagId;
    }

    public void setBloodBagId(Long bloodBagId) {
        this.bloodBagId = bloodBagId;
    }
}
