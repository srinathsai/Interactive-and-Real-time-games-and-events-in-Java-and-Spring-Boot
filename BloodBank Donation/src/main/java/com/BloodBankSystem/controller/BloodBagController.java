package com.BloodBankSystem.controller;

import com.BloodBankSystem.entity.BloodBag;
import com.BloodBankSystem.service.BloodBagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/bloodbags")
public class BloodBagController {

    private final BloodBagService bloodBagService;



    @Autowired
    public BloodBagController(BloodBagService bloodBagService) {
        this.bloodBagService = bloodBagService;
    }

    //endpoint 7
    @GetMapping("/all")
    public ResponseEntity<Object> getAllBloodBags() {
        Map<String,Object> response=new LinkedHashMap<>();
        List<BloodBag> bloodBags = bloodBagService.getAllBloodBags();
        if(bloodBags.isEmpty()){
            response.put("success", false);
            response.put("message", "No BloodBag found till date");
            return ResponseEntity.ok(response);
        }

        List<Map<String,Object>> responses = new ArrayList<>();
        for(BloodBag bloodBag : bloodBags){
            Map<String,Object>response1 = new LinkedHashMap<>();
            response1.put("bloodBagId", bloodBag.getBloodBagId());
            response1.put("donatedAt",bloodBag.getDonatedAt());
            response1.put("qty",bloodBag.getQty());
            response1.put("bloodType",bloodBag.getBloodType());
            responses.add(response1);
        }


        return ResponseEntity.ok(responses.toArray());
    }
}

