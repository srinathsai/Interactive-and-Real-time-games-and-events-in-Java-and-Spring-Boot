package com.BloodBankSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "donations")
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long donationId;

    @ManyToOne
    @JoinColumn(name = "donor_id") // Use the actual foreign key column name
    private Donor donor;


    @Column(name = "qty")
    private int qty;

    @Column(name = "donated_at")
    private LocalDate donatedAt;

    @Column(name = "blood_type")
    private String bloodType;

    // Constructors


    // Getters and Setters

    public Long getDonationId(){
        return this.donationId;
    }

    public Donor getDonor(){
        return this.donor;
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

    public void setDonor(Donor donor){
        this.donor = donor;
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
