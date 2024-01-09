package com.BloodBankSystem.repository;

import com.BloodBankSystem.entity.BloodBag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloodBagRepository extends JpaRepository<BloodBag, Long> {
}
