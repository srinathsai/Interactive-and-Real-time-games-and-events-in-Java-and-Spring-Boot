package com.BloodBankSystem.controller;

import com.BloodBankSystem.entity.BloodBag;
import com.BloodBankSystem.entity.BloodUsage;
import com.BloodBankSystem.entity.Donor;
import com.BloodBankSystem.service.BloodUsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@RestController
@RequestMapping("/bloodusage")
public class BloodUsageController {

    private final BloodUsageService bloodUsageService;

    private Map<String, Set<String>> compatibilityMap;
    private Set<String> BloodTypes;
    @Autowired
    public BloodUsageController(BloodUsageService bloodUsageService) {
        this.bloodUsageService = bloodUsageService;
        compatibilityMap = new HashMap<>();
        BloodTypes = new HashSet<>();
        BloodTypes.add("A+");
        BloodTypes.add("A-");
        BloodTypes.add("B+");
        BloodTypes.add("B-");
        BloodTypes.add("AB+");
        BloodTypes.add("AB-");
        BloodTypes.add("O+");
        BloodTypes.add("O-");
        for(String x : BloodTypes){
            Set<String> acceptableBlood = new HashSet<>();
            compatibilityMap.put(x,acceptableBlood);
        }
        compatibilityMap.get("A+").add("A+");
        compatibilityMap.get("A+").add("A-");
        compatibilityMap.get("A+").add("O+");
        compatibilityMap.get("A+").add("O-");
        compatibilityMap.get("A-").add("A-");
        compatibilityMap.get("A-").add("O-");
        compatibilityMap.get("B+").add("B+");
        compatibilityMap.get("B+").add("B-");
        compatibilityMap.get("B+").add("O+");
        compatibilityMap.get("B+").add("O-");
        compatibilityMap.get("B-").add("B-");
        compatibilityMap.get("B-").add("O-");
        compatibilityMap.get("AB+").add("AB+");
        compatibilityMap.get("AB+").add("AB-");
        compatibilityMap.get("AB+").add("A+");
        compatibilityMap.get("AB+").add("A-");
        compatibilityMap.get("AB+").add("O+");
        compatibilityMap.get("AB+").add("O-");
        compatibilityMap.get("AB+").add("B+");
        compatibilityMap.get("AB+").add("B-");
        compatibilityMap.get("AB-").add("AB-");
        compatibilityMap.get("AB-").add("A-");
        compatibilityMap.get("AB-").add("B-");
        compatibilityMap.get("AB-").add("O-");
        compatibilityMap.get("O+").add("O+");
        compatibilityMap.get("O+").add("O-");
        compatibilityMap.get("O-").add("O-");


    }

    //endpoint 8
    @PostMapping("/request")
    public ResponseEntity<Object> requestBlood(@RequestBody Map<String, Object> request) {
        String bloodType = request.get("bloodType").toString();
        Integer qtyNeeded = Integer.parseInt(request.get("qtyNeeded").toString());
        Map<String,Object> response=new LinkedHashMap<>();

        if(bloodType== null || qtyNeeded==null){
            response.put("error","You must enter both Quantity needed and bloodType and these can't be null");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
        else if(bloodType.length()==0){
            response.put("error","BloodType can't be empty");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
        else if(qtyNeeded <=0){
            response.put("error","Required Quantity should be strictly greatly than 0");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
        else if(!compatibilityMap.containsKey(bloodType)){
            response.put("error","Please give valid blood type");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }

        List<BloodBag> allbloodBags = bloodUsageService.getAllBloodBags();

        List<Donor> allDonors = bloodUsageService.getAllDonors();

        List<BloodBag> compatibleBloodBags = new ArrayList<>();

        List<Donor> compatibleDonors = new ArrayList<>();

        LocalDate currentDate = LocalDate.now();

        for(BloodBag bloodBag : allbloodBags){
            int days = Period.between(bloodBag.getDonatedAt(),currentDate).getDays();
            if(compatibilityMap.get(bloodType).contains(bloodBag.getBloodType()) && days<42){
                compatibleBloodBags.add(bloodBag);
            }
        }

        for(Donor donor: allDonors){
            if(compatibilityMap.get(bloodType).contains(donor.getBloodType())){
                compatibleDonors.add(donor);
            }
        }

        if(compatibleBloodBags.isEmpty() && compatibleDonors.isEmpty()){
            response.put("success", false);
            response.put("message", "We have not found blood in the blood bank, and no donor with bloodtype found");
            return ResponseEntity.ok(response);
        }

        if(compatibleBloodBags.isEmpty() && !compatibleDonors.isEmpty()){
            response.put("message,","We have not found blood in the blood bank, so we have below list of donors");
            response.put("Donors",compatibleDonors.toArray());
            return ResponseEntity.ok(response);
        }


        List<BloodBag> requestedBags = new ArrayList<>();
        int index =0;
        int qtyNeeded1= qtyNeeded;
        for(BloodBag bloodBag :compatibleBloodBags){
            if(bloodBag.getQty()>=qtyNeeded1){
                requestedBags.add(bloodBag);
                qtyNeeded1=compatibleBloodBags.get(index).getQty()-qtyNeeded1;
                if(qtyNeeded1==0){
                    bloodUsageService.DeleteBloodBag(compatibleBloodBags.get(index));
                }
                else{
                    BloodBag UpdatedBloodBag = bloodUsageService.updateBloodBag(compatibleBloodBags.get(index).getBloodBagId(),qtyNeeded1);
                }
                break;

            }
        }


        if(requestedBags.size()>0){
            response.put("message","We have found" + " "+ String.valueOf(qtyNeeded) + " "+"ml" + "blood in the bank");
            List<BloodUsage> bloodUsages = new ArrayList<>();
            for(BloodBag bloodBag: requestedBags){
                BloodUsage bloodUsage = new BloodUsage();
                bloodUsage.setQty(qtyNeeded);
                bloodUsage.setBloodBagId(bloodBag.getBloodBagId());
                bloodUsage.setBloodType(bloodBag.getBloodType());
                bloodUsage.setUsedAt(currentDate);
                bloodUsages.add(bloodUsage);

            }

            List<Map<String,Object>> responses = new ArrayList<>();
            for(BloodUsage bloodUsage: bloodUsages){
                BloodUsage savedBloodUsage = bloodUsageService.saveBloodUsage(bloodUsage);
                Map<String,Object> response1 = new LinkedHashMap<>();
                response1.put("bloodUsageId",savedBloodUsage.getId());
                response1.put("qty",savedBloodUsage.getQty());
                response1.put("bloodType",savedBloodUsage.getBloodType());
                response1.put("usedAt",savedBloodUsage.getUsedAt());
                response1.put("bloodBagId",savedBloodUsage.getBloodBagId());
                responses.add(response1);
            }

            response.put("bloodUsage",responses.toArray());
            return ResponseEntity.ok(response);
        }

        else{
            List<BloodUsage> partialBloodBagUsages = new ArrayList<>();
            int x =0;
            int qtyNeeded2 = qtyNeeded;
            for(BloodBag bloodBag : compatibleBloodBags){
                if(bloodBag.getQty()< qtyNeeded2){
                    qtyNeeded2-= bloodBag.getQty();
                    x+= bloodBag.getQty();
                    BloodUsage bloodUsage = new BloodUsage();
                    bloodUsage.setQty(bloodBag.getQty());
                    bloodUsage.setBloodBagId(bloodBag.getBloodBagId());
                    bloodUsage.setBloodType(bloodBag.getBloodType());
                    bloodUsage.setUsedAt(currentDate);
                    partialBloodBagUsages.add(bloodUsage);

                    bloodUsageService.DeleteBloodBag(bloodBag);
                    if(qtyNeeded2==0){
                        break;
                    }
                }
            }

            if(qtyNeeded2!=0){
                response.put("message1", "We have found " + " " + String.valueOf(x) + " "+ "ml" + " "+ "of blood in the blood bank");
                List<Map<String,Object>> bloodUsageResponses = new ArrayList<>();
                for(BloodUsage bloodUsage : partialBloodBagUsages){
                    BloodUsage savedBloodUsage = bloodUsageService.saveBloodUsage(bloodUsage);
                    Map<String,Object> response1 = new LinkedHashMap<>();
                    response1.put("bloodUsageId",savedBloodUsage.getId());
                    response1.put("qty",savedBloodUsage.getQty());
                    response1.put("bloodType",savedBloodUsage.getBloodType());
                    response1.put("usedAt",savedBloodUsage.getUsedAt());
                    response1.put("bloodBagId",savedBloodUsage.getBloodBagId());
                    bloodUsageResponses.add(response1);

                }
                response.put("bloodUsage", bloodUsageResponses.toArray());


                if(compatibleDonors.size()>0){
                    List<Map<String,Object>>DonorResponses = new ArrayList<>();

                    for(Donor savedDonor : compatibleDonors){
                        Map<String,Object> response1 = new LinkedHashMap<>();
                        response1.put("donorId", savedDonor.getDonorId());
                        response1.put("name", savedDonor.getName());
                        response1.put("phoneNumber", savedDonor.getPhoneNumber());
                        response1.put("address", savedDonor.getAddress());
                        response1.put("dateOfBirth", savedDonor.getDateOfBirth());
                        response1.put("gender", savedDonor.getGender());
                        response1.put("bloodType", savedDonor.getBloodType());
                        response1.put("medicalHistory", savedDonor.getMedicalHistory());
                        DonorResponses.add(response1);
                    }

                    response.put("message2","For Remaining blood of" + String.valueOf(qtyNeeded-x) +" "+ " ml,"+ " "+ "we have below List of Donors");
                    response.put("Donors",DonorResponses.toArray());
                    return ResponseEntity.ok(response);

                }

                else{
                    response.put("message2","For Remaining blood of" + String.valueOf(qtyNeeded-x) + " "+ " ml,"+ " "+ "we cannot find list of donors");
                    return ResponseEntity.ok(response);
                }
            }

            //List<BloodUsage> partialBloodUsages = new ArrayList<>();
            List<Map<String,Object>> bloodUsageResponses = new ArrayList<>();
            for(BloodUsage bloodUsage : partialBloodBagUsages){
                BloodUsage savedBloodUsage = bloodUsageService.saveBloodUsage(bloodUsage);
                Map<String,Object> response1 = new LinkedHashMap<>();
                response1.put("bloodUsageId",savedBloodUsage.getId());
                response1.put("qty",savedBloodUsage.getQty());
                response1.put("bloodType",savedBloodUsage.getBloodType());
                response1.put("usedAt",savedBloodUsage.getUsedAt());
                response1.put("bloodBagId",savedBloodUsage.getBloodBagId());
                bloodUsageResponses.add(response1);

            }
            response.put("message","We have found" + String.valueOf(qtyNeeded) + " "+"ml" + "blood in the bank");
            response.put("bloodUsage",bloodUsageResponses.toArray());
            return ResponseEntity.ok(response);

        }







    }
}
