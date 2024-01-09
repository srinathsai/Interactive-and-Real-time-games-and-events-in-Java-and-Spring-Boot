package com.BloodBankSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Table(name = "blood_bags")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BloodBag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bloodBagId;

    @Column(name = "donated_at")
    private LocalDate donatedAt;

    @Column(name = "qty")
    private int qty;

    @Column(name = "blood_type")
    private String bloodType;

    // Constructors

    // Getters and Setters

    public Long getBloodBagId(){
        return this.bloodBagId;
    }

    public int getQty(){
        return this.qty;
    }

    public LocalDate getDonatedAt(){
        return this.donatedAt;
    }

    public String getBloodType(){
        return this.bloodType;
    }



    public void setQty(int qty){
        this.qty = qty;
    }

    public void setDonatedAt(LocalDate donatedAt){
        this.donatedAt = donatedAt;
    }

    public void setBloodType(String bloodType){
        this.bloodType = bloodType;
    }

}
