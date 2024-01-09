package com.BloodBankSystem.repository;

import com.BloodBankSystem.entity.BloodUsage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloodUsageRepository extends JpaRepository<BloodUsage, Long> {
    //Add custom query if needed
}
