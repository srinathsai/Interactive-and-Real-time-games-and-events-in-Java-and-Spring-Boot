package com.BloodBankSystem.service;

import com.BloodBankSystem.entity.BloodBag;
import com.BloodBankSystem.entity.BloodUsage;
import com.BloodBankSystem.entity.Donor;
import com.BloodBankSystem.repository.BloodBagRepository;
import com.BloodBankSystem.repository.BloodUsageRepository;
import com.BloodBankSystem.repository.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloodUsageService {

    private final BloodUsageRepository bloodUsageRepository;
    private final BloodBagRepository bloodBagRepository;
    private final DonorRepository donorRepository;

    @Autowired
    public BloodUsageService(BloodUsageRepository bloodUsageRepository, BloodBagRepository bloodBagRepository, DonorRepository donorRepository) {
        this.bloodUsageRepository = bloodUsageRepository;
        this.bloodBagRepository = bloodBagRepository;
        this.donorRepository = donorRepository;
    }

    public List<BloodBag> getAllBloodBags(){
        List<BloodBag> bloodbags = bloodBagRepository.findAll();
        return bloodbags;
    }

    public List<Donor> getAllDonors(){
        List<Donor> donors = donorRepository.findAll();
        return donors;
    }

    public void DeleteBloodBag(BloodBag bloodBag){
        bloodBagRepository.deleteById(bloodBag.getBloodBagId());
    }

    public BloodUsage saveBloodUsage(BloodUsage bloodUsage){
        return bloodUsageRepository.save(bloodUsage);
    }

    public BloodBag updateBloodBag(Long id, int qty){
        BloodBag retrievedBloodBag = bloodBagRepository.findById(id).get();
        retrievedBloodBag.setQty(qty);
        return bloodBagRepository.save(retrievedBloodBag);

    }


}