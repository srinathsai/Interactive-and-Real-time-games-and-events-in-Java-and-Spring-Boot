package com.BloodBankSystem.service;

import com.BloodBankSystem.entity.Donor;
import com.BloodBankSystem.repository.DonorRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class DonorService {
    private DonorRepository donorRepository;


    @Autowired
    public DonorService(DonorRepository donorRepository) {
        this.donorRepository = donorRepository;

    }

    public boolean isPhoneNumberInUse(String phoneNumber) {
        return donorRepository.existsByPhoneNumber(phoneNumber);
    }

    public Donor addDonor(Donor donor){
        return donorRepository.save(donor);
    }

    public List<Donor> getAllDonors(){
        List<Donor>donors= donorRepository.findAll();
        return donors;
    }

    public boolean isIdExist(Long donorId){
        return donorRepository.existsById(donorId);
    }

    public Donor getDonorById(Long donorId){
        return donorRepository.findById(donorId).get();


    }
}
