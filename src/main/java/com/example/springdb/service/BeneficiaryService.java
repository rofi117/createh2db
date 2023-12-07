package com.example.springdb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.springdb.model.Beneficiary;
import com.example.springdb.repo.BeneficiaryRepo;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class BeneficiaryService {

    @Autowired
    BeneficiaryRepo repo;
    public Beneficiary save(Beneficiary beneficiary) {
        return repo.save(beneficiary);
    }

    public List<Beneficiary> findAll(){
        List<Beneficiary> data = repo.findAll();
        System.out.println("Beneficiary:"+ data);
        return repo.findAll();
    }

    public String deleteAll()
    {
        repo.deleteAll();
        return "All Beneficiary Records Deleted Successfully";
    }

    public boolean deleteBeneficiaryByID(Integer id)
    {
        repo.deleteById(id);
        return true;
    }

    public boolean checkBeneficiaryExistById(Integer id)
    {
        repo.existsById(id);
        return true;
    }

    public Beneficiary updateBeneficiaryById(Beneficiary beneficiary, Integer id) {
        Beneficiary updateBeneficiary = new Beneficiary();
        updateBeneficiary.setId(id);
        updateBeneficiary.setBeneficiaryName(beneficiary.getBeneficiaryName());
        updateBeneficiary.setBeneficiaryAccNo(beneficiary.getBeneficiaryAccNo());
        updateBeneficiary.setBeneficiaryBank(beneficiary.getBeneficiaryBank());
        updateBeneficiary.setBeneficiaryIFSC(beneficiary.getBeneficiaryIFSC());
        return repo.save(updateBeneficiary);
    }

    public boolean isValidBankAccNumber(String bankAccNo)
    {
        String regex ="^\\d{10}$";
        Pattern p= Pattern.compile(regex);
        if(bankAccNo == null){
            return false;
        }
        Matcher m=p.matcher(bankAccNo);
        if(m.matches())
            return true;
        return false;
    }

    public Optional<Beneficiary> findBeneficiaryById(Integer id) {
        return repo.findById(id);
    }
}
