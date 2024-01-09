package com.BloodBankSystem.repository;

import com.BloodBankSystem.entity.Donation;
import com.BloodBankSystem.entity.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {
    //Add custom query if needed
    Donation findDonation_ByDonor(Donor donor);
}