package com.BloodBankSystem.service;

import com.BloodBankSystem.entity.BloodBag;
import com.BloodBankSystem.entity.Donation;
import com.BloodBankSystem.entity.Donor;
import com.BloodBankSystem.repository.BloodBagRepository;
import com.BloodBankSystem.repository.DonationRepository;
import com.BloodBankSystem.repository.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DonationService {

    private final DonationRepository donationRepository;
    private final DonorRepository donorRepository;
    private final BloodBagRepository bloodBagRepository;

    @Autowired
    public DonationService(DonationRepository donationRepository, DonorRepository donorRepository, BloodBagRepository bloodBagRepository) {
        this.donationRepository = donationRepository;
        this.donorRepository = donorRepository;
        this.bloodBagRepository = bloodBagRepository;
    }

    public Donation addDonation(Donation donation){
        Donation savedDonation = donationRepository.save(donation);
        List<BloodBag> bloodBags = bloodBagRepository.findAll();
        boolean found = false;
        BloodBag existingBloodBag=null;
        for( BloodBag bloodbag : bloodBags){
            if(donation.getDonatedAt().equals(bloodbag.getDonatedAt()) && donation.getBloodType().equals(bloodbag.getBloodType())){
                existingBloodBag = bloodbag;
                found = true;
                break;
            }
        }

        if(found==true){
            int total = donation.getQty() + existingBloodBag.getQty();
            existingBloodBag.setQty(total);
            bloodBagRepository.save(existingBloodBag);
        }

        else{
            BloodBag newBloodBag = new BloodBag();
            newBloodBag.setBloodType(donation.getBloodType());
            newBloodBag.setQty(donation.getQty());
            newBloodBag.setDonatedAt(donation.getDonatedAt());
            bloodBagRepository.save(newBloodBag);
        }
        return savedDonation;

    }

    //public Donation findById(Long )
    public Donation findDonationByDonor(Donor donor){
        Donation donation = donationRepository.findDonation_ByDonor(donor);
        return donation;

    }

    public List<Donation> getAllDonations(){
        List<Donation> donations= donationRepository.findAll();
        return donations;
    }

    public List<Donation> getAllDonationsByDonor(Donor donor){
        List<Donation> db_donations = donationRepository.findAll();
        List<Donation> donor_donations = new ArrayList<>();
        for(Donation donation : db_donations){
            if(donation.getDonor().getDonorId()== donor.getDonorId()){
                donor_donations.add(donation);
            }
        }
        return donor_donations;
    }


}