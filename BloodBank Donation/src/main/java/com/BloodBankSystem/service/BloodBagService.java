package com.BloodBankSystem.service;

import com.BloodBankSystem.entity.BloodBag;
import com.BloodBankSystem.repository.BloodBagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloodBagService {

    private final BloodBagRepository bloodBagRepository;

    @Autowired
    public BloodBagService( BloodBagRepository bloodBagRepository) {

        this.bloodBagRepository = bloodBagRepository;
    }

    public List<BloodBag> getAllBloodBags(){
        List<BloodBag> bloodBags = bloodBagRepository.findAll();
        return bloodBags;
    }
}