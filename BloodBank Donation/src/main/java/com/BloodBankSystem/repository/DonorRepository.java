package com.BloodBankSystem.repository;

import com.BloodBankSystem.entity.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonorRepository extends JpaRepository<Donor,Long> {
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsById(Long donorId);
}
