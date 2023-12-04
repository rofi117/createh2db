package com.example.springdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.springdb.model.Beneficiary;
import com.example.springdb.service.BeneficiaryService;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@RestController
public class BeneficiaryController {

    @Autowired
    BeneficiaryService service;
    @PostMapping("/addbeneficiary")
    public ResponseEntity<Beneficiary> addBeneficiary(@RequestBody Beneficiary beneficiary) {
        System.out.println("AddBeneficiary");
        Beneficiary newbeneficiary = service.save(beneficiary);
        return new ResponseEntity<Beneficiary>(newbeneficiary, HttpStatus.CREATED);
    }

    @GetMapping("/beneficiary")
    public List<Beneficiary> getAllBeneficiary(){
        return service.findAll();
    }

    @DeleteMapping("/deleteallBeneficiary")
    public String deleteBeneficiary()
    {
        return service.deleteAll();
    }

    @DeleteMapping("/delete_benefciary_by_id{id}")
    public ResponseEntity<String> deleteBeneficiaryByID(@PathVariable("id") Integer id)
    {
        boolean isBeneficiaryExist = service.checkBeneficiaryExistById(id);
        if(!isBeneficiaryExist)
            return new ResponseEntity<String>("Beneficiary id="+id+"is not exist",HttpStatus.NOT_FOUND);
        else {
            service.deleteBeneficiaryByID(id);
        }

        return new ResponseEntity<String>("Beneficiary id = "+id +" is successfully deleted",HttpStatus.OK);

    }

    @PutMapping("/update-beneficiary/{id}")
    public ResponseEntity<Beneficiary> updateBeneficiary(@RequestBody Beneficiary beneficiary, @PathVariable("id") Integer id) {
        Beneficiary updatedbeneficiary = service.updateBeneficiaryById(beneficiary, id);
        return new ResponseEntity<Beneficiary>(updatedbeneficiary, HttpStatus.OK);

    }




}
