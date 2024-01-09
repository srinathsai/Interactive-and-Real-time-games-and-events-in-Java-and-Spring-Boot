package com.BloodBankSystem.controller;

import com.BloodBankSystem.entity.Donor;
import com.BloodBankSystem.service.DonorService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/donors")
@NoArgsConstructor
public class DonorController {
    private  DonorService donorService;
    private Set<String> BloodTypes;

    @Autowired
    public DonorController(DonorService donorService) {
        this.donorService = donorService;
        BloodTypes = new HashSet<>();
        BloodTypes.add("A+");
        BloodTypes.add("A-");
        BloodTypes.add("B+");
        BloodTypes.add("B-");
        BloodTypes.add("AB+");
        BloodTypes.add("AB-");
        BloodTypes.add("O+");
        BloodTypes.add("O-");
    }

    //endpoint 1
    @PostMapping
    public ResponseEntity<Object> addDonor(@RequestBody Donor donor) {
        Map<String,Object> response=new LinkedHashMap<>();
        if(donor.getName()==null || donor.getName().length()==0 || donor.getPhoneNumber()==null || donor.getPhoneNumber().length()==0 || donor.getAddress()==null || donor.getAddress().length()==0 || donor.getDateOfBirth()== null  || donor.getGender()==null || donor.getGender().length()==0 || donor.getBloodType()==null || donor.getBloodType().length()==0 || donor.getMedicalHistory()==null || donor.getMedicalHistory().length()==0){
            response.put("error","Donor information is incomplete");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }

        if(!BloodTypes.contains(donor.getBloodType())){
            response.put("error","Blood type is not valid");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }

        for(int i=0;i<donor.getPhoneNumber().length();i++){
            char ch = donor.getPhoneNumber().charAt(i);
            if(!Character.isDigit(ch)){
                response.put("error","Phone number is not valid");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            }
        }

        if(donorService.isPhoneNumberInUse(donor.getPhoneNumber())){
            response.put("error","Phone number is already in use");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }


        Donor savedDonor = donorService.addDonor(donor);



        //Map<String,Object> response=new LinkedHashMap<>();
        //response.put("success", true);
        //response.put("message", "Donnor added successfully");
        response.put("donorId", savedDonor.getDonorId());
        response.put("name", savedDonor.getName());
        response.put("phoneNumber", savedDonor.getPhoneNumber());
        response.put("address", savedDonor.getAddress());
        response.put("dateOfBirth", savedDonor.getDateOfBirth());
        response.put("gender", savedDonor.getGender());
        response.put("bloodType", savedDonor.getBloodType());
        response.put("medicalHistory", savedDonor.getMedicalHistory());
        return  ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //endpoint 2
    @GetMapping
    public ResponseEntity<Object> getAllDonors() {
        Map<String,Object> response=new LinkedHashMap<>();
        List<Donor> donors = donorService.getAllDonors();
        if(donors.size()==0){
            response.put("success", false);
            response.put("message", "Donors not found");
        }
        else{
            return new ResponseEntity<>(donors, HttpStatus.OK);
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //endpoint 3
    @GetMapping("/{donorId}")
    public ResponseEntity<Object> getDonorById(@PathVariable Long donorId) {
        Donor donor = null;
        if(donorService.isIdExist(donorId)){
            donor = donorService.getDonorById(donorId);
        }
        //System.out.println(donor.toString());
        if(donor!= null){
            Map<String,Object> response=new LinkedHashMap<>();
            //Donor donor1 = donor.get();
            //response.put("success", true);
            response.put("donorId", donor.getDonorId());
            response.put("name", donor.getName());
            response.put("phoneNumber", donor.getPhoneNumber());
            response.put("address", donor.getAddress());
            response.put("dateOfBirth", donor.getDateOfBirth());
            response.put("gender", donor.getGender());
            response.put("bloodType", donor.getBloodType());
            response.put("medicalHistory", donor.getMedicalHistory());

            //response.put("message", donor1);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        }
        else {
            Map<String,Object> response=new LinkedHashMap<>();
            response.put("success", false);
            response.put("message", String.format("Donor not found with ID: %d", donorId));
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }






    }
}
