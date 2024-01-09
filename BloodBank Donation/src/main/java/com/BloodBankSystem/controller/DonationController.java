package com.BloodBankSystem.controller;

import com.BloodBankSystem.entity.Donation;
import com.BloodBankSystem.entity.Donor;
import com.BloodBankSystem.service.DonationService;
import com.BloodBankSystem.service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/donations")
public class DonationController {

    private final DonationService donationService;
    private final DonorService donorService;

    @Autowired
    public DonationController(DonationService donationService, DonorService donorService) {
        this.donationService = donationService;
        this.donorService = donorService;
    }

    //endpoint 4
    @PostMapping("/add")
    public ResponseEntity<Object> addDonation(@RequestBody Map<String, Object> requestBody) {
        Long donorId = Long.parseLong(requestBody.get("donorId").toString());
        Integer qty = Integer.parseInt(requestBody.get("qty").toString());
        Map<String, Object> response = new LinkedHashMap<>();
        if (donorId != null && qty > 0) {
            Donor donor = null;
            if (donorService.isIdExist(donorId)) {
                donor = donorService.getDonorById(donorId);
            }
            if (donor == null) {
                response.put("error", "Donor was not found");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            }
            LocalDate currentDate = LocalDate.now();
            LocalDate donorBdayDate = donor.getDateOfBirth();
            int age = Period.between(donorBdayDate, currentDate).getYears();
            System.out.println(age);
            if (age < 18 || age > 65) {
                response.put("error", "Age of donation is not in the range");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            }
            Donation donation = donationService.findDonationByDonor(donor);
            if (donation != null) {
                int days = Period.between(donation.getDonatedAt(), currentDate).getDays();
                if (days <56) {
                    response.put("error", "You should wait atleast 56 days after your donation");
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
                }
            }
            if (qty > 470) {
                response.put("error", "Donation quantity is out of allowable range");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            }

            Donation newDonation = new Donation();
            newDonation.setDonor(donor);
            newDonation.setDonatedAt(currentDate);
            newDonation.setQty(qty);
            newDonation.setBloodType(donor.getBloodType());
            Donation savedDonation = donationService.addDonation(newDonation);

            //response.put("success", true);
            //response.put("message", "Donnor added successfully");
            response.put("donationId", savedDonation.getDonationId());
            response.put("donorId", savedDonation.getDonor().getDonorId());
            response.put("donatedAt", savedDonation.getDonatedAt());
            response.put("qty", savedDonation.getQty());
            response.put("bloodType", savedDonation.getBloodType());
            return ResponseEntity.status(HttpStatus.OK).body(response);


        } else {
            response.put("error", "You must give Qty>0 and donorId");
            //response.put();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
    }

    //endpoint 5
    @GetMapping("/all")
    public ResponseEntity<Object> getAllDonations() {
        List<Donation> donations = donationService.getAllDonations();
        Map<String, Object> response = new LinkedHashMap<>();
        if (donations.size() == 0) {
            response.put("success", false);
            response.put("message", "No donations found till date");
            return ResponseEntity.ok(response);
        } else {
            List<Map<String, Object>> responses = new ArrayList<>();
            for (Donation donation : donations) {
                Map<String,Object> response1=new LinkedHashMap<>();
                response1.put("donationId", donation.getDonationId());
                response1.put("donorId", donation.getDonor().getDonorId());
                response1.put("donatedAt",donation.getDonatedAt());
                response1.put("qty", donation.getQty());
                response1.put("bloodType", donation.getBloodType());
                responses.add(response1);
                //response.clear();
            }
            return ResponseEntity.ok(responses.toArray());
        }


    }

    //endpoint 6
    @GetMapping("/history/{donorId}")
    public ResponseEntity<Object> getDonationHistory(@PathVariable Long donorId) {
        if(!donorService.isIdExist(donorId)){
            Map<String, Object> response = new LinkedHashMap<>();
            //response.put("success", true);
            response.put("error", "There is no donor with given donor id");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);

        }
        Donor donor = donorService.getDonorById(donorId);
        List<Donation> donor_donations = donationService.getAllDonationsByDonor(donor);
        List<Map<String,Object>> donor_history = new ArrayList<>();
        for(Donation donation : donor_donations){
            Map<String,Object> response1=new LinkedHashMap<>();
            response1.put("donationId", donation.getDonationId());
            response1.put("donorId", donation.getDonor().getDonorId());
            response1.put("donatedAt",donation.getDonatedAt());
            response1.put("qty", donation.getQty());
            response1.put("bloodType", donation.getBloodType());
            donor_history.add(response1);

        }
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("donorId", donor.getDonorId());
        response.put("name", donor.getName());
        response.put("phoneNumber", donor.getPhoneNumber());
        response.put("address", donor.getAddress());
        response.put("dateOfBirth", donor.getDateOfBirth());
        response.put("gender", donor.getGender());
        response.put("bloodType", donor.getBloodType());
        response.put("medicalHistory", donor.getMedicalHistory());
        response.put("donationHistory",donor_history.toArray());
        return ResponseEntity.ok(response);
    }
}