package com.example.springdb.controller;

import com.example.springdb.model.Beneficiary;
import com.example.springdb.service.BeneficiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;



@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api")
public class BeneficiaryController {

    @Autowired
    BeneficiaryService service;


    @PostMapping("/addbeneficiary")
    public ResponseEntity<?> addBeneficiary(@RequestBody Beneficiary beneficiary) {
        System.out.println("AddBeneficiary");
        if (!service.isValidBankAccNumber(beneficiary.getBeneficiaryAccNo()))
            return new ResponseEntity<String>("Entered Account number is not valid", HttpStatus.NOT_ACCEPTABLE);
        else {
            Beneficiary newbeneficiary = service.save(beneficiary);
            return new ResponseEntity<Beneficiary>(newbeneficiary, HttpStatus.CREATED);
        }
    }

    @GetMapping("/getAllbeneficiary")
    public List<Beneficiary> getAllBeneficiary() {
        return service.findAll();
    }

    @DeleteMapping("/deleteallBeneficiary")
    public ResponseEntity<String> deleteBeneficiary() {
        String emptymessage = "Beneficiary Data already deleted, List is empty";
        List<Beneficiary> beneficiaryList=service.findAll();
        if(beneficiaryList.isEmpty()) {
             return new ResponseEntity<String>(emptymessage,HttpStatus.NO_CONTENT);
        }
        else {
            String result = service.deleteAll();
            return new ResponseEntity<String>(result, HttpStatus.OK);
        }



    }

    @DeleteMapping("/delete-beneficiary-By-id/{id}")
    public ResponseEntity<String> deleteBeneficiaryById(@PathVariable("id") Integer id){
        if(!service.checkBeneficiaryExistById(id)){
            return new ResponseEntity<String>("Beneficiary id = " + id + " is not exist", HttpStatus.NOT_FOUND);}
        else{
              service.deleteBeneficiaryByID(id);
              return new ResponseEntity<String>("Beneficiary id = " + id + " is successfully deleted", HttpStatus.OK);
          }

    }

    @PutMapping("/update-beneficiary/{id}")
    public ResponseEntity<Beneficiary> updateBeneficiary(@RequestBody Beneficiary beneficiary, @PathVariable("id") Integer id) {
        Beneficiary updatedbeneficiary = service.updateBeneficiaryById(beneficiary, id);
        return new ResponseEntity<Beneficiary>(updatedbeneficiary, HttpStatus.OK);
    }

    @GetMapping("/get-beneficiary-By-id/{id}")
    public ResponseEntity<?> getBeneficiaryById(@PathVariable("id") Integer id) {
     Optional<Beneficiary> beneficiaryById = service.findBeneficiaryById(id);

     if(beneficiaryById.isEmpty())
         return new ResponseEntity<String>("The given beneficiary id is not found",HttpStatus.NOT_FOUND);

     return new ResponseEntity<Beneficiary>(beneficiaryById.get(), HttpStatus.FOUND);

    }











}
